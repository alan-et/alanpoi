package com.alanpoi.analysis.excel.imports.handle;

import com.alanpoi.analysis.common.ExecutorTools;
import com.alanpoi.analysis.excel.exports.handle.ExportHandle;
import com.alanpoi.common.enums.ResponseEnum;
import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.analysis.excel.imports.*;
import com.alanpoi.common.util.NetworkUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * excel核心处理类
 *
 * @author zhuoxun.peng
 * @since 2020-2-26
 */
public class ExcelHandle {
    protected static final Logger log = LoggerFactory.getLogger(ExportHandle.class);

    public ExcelWorkbookManage excelWorkbookManage;

    public ExecutorTools executorTools;

    public StringRedisTemplate redisTemplate;

    @Value("${server.port:8080}")
    public String port;

    @Value("${excel.tmp_path:/tmp/import/}")
    public String tmpPath;

    @Value("${excel.download_path:/download/}")
    public String downloadPath;

    public ExcelImportRes process(String workbookId, List<ExcelSheetData> sheetDataList, Excel excel) {
        log.info("ExcelHandle.process ");
        Class<? extends ExcelConsumeInterface> c = excel.getConsume();
        String fileName = excel.getFileName();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        int total = sheetDataList.get(0).getData().size();
        final ExcelImportRes excelImportRes = new ExcelImportRes();
        ExcelConsumeInterface consumeInterface = ApplicationUtil.getBean(c);
        consumeInterface.validData(workbookId, sheetDataList, excel.getCustomParam());
        ExcelError excelError = excelWorkbookManage.getExcelError(workbookId);
        int rowStart = sheetDataList.get(0).getRowStart();
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            if (excelError != null && !CollectionUtils.isEmpty(excelError.getSheetErrors())) {
                Map<String, ExcelImportRes.SheetInfo> sheetInfoMap = new HashMap<>();
                sheetDataList.forEach(e -> {
                    ExcelImportRes.SheetInfo sheetInfo = new ExcelImportRes.SheetInfo();
                    int sTotal = e.getData().size();
                    List<Object> errorDataList = new ArrayList<>();
                    e.getData().forEach(vo -> {
                        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(vo));
                        //filter mismatch sheet error
                        List<ExcelError.SheetError> sheetErrors = excelError.getSheetErrors().stream().filter(fe -> fe.getIndex() == e.getIndex()).collect(Collectors.toList());
                        sheetErrors.forEach(er -> {
                            er.getRowErrors().forEach(re -> {
                                if (re.getRowIndex() == jsonObject.getInteger("rowIndex")) {
                                    errorDataList.add(vo);
                                }
                            });
                        });
                    });
                    //Record the import details of each sheet
                    int sucNum = sTotal - errorDataList.size();
                    int failNum = errorDataList.size();
                    sheetInfo.setIndex(e.getIndex());
                    sheetInfo.setSucNum(sucNum);
                    sheetInfo.setFailNum(failNum);
                    sheetInfo.setSheetName(e.getSheetName());
                    sheetInfoMap.put(e.getSheetName(), sheetInfo);
                    log.info("ExcelSheet({}) import success:{},error:{}", e.getSheetName(), sucNum, failNum);
                    e.getData().removeAll(errorDataList);
                });
                excelImportRes.setErrorMap(sheetInfoMap);
            }
            RequestContextHolder.setRequestAttributes(requestAttributes, true);
            try {

                if ("true".equals(excel.getSupportPart())) consumeInterface.end(sheetDataList, excel.getCustomParam());
                else {
                    if (excelError == null ||
                            CollectionUtils.isEmpty(excelError.getSheetErrors()))
                        consumeInterface.end(sheetDataList, excel.getCustomParam());
                }
            } catch (Exception e) {
                log.error("", e);
            }
            return null;

        });
        CompletableFuture<ErrorFile> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            if (excelError != null &&
                    !CollectionUtils.isEmpty(excelError.getSheetErrors())) {
                String newFileName = "";
                if (StringUtils.isNotEmpty(fileName)) {
                    String filePre = fileName.substring(0, fileName.lastIndexOf("."));
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    StringBuffer sb = new StringBuffer(filePre);
                    newFileName = sb.append("_").append(new Date().getTime()).append(suffix).toString();
                } else {
                    newFileName = new Date().getTime() + ".xlsx";
                }
                return writeError(workbookId, excelError, excelWorkbookManage.getWorkbook(workbookId), newFileName, rowStart);
            }
            return null;
        });
        CompletableFuture.allOf(completableFuture, completableFuture1).join();
        ErrorFile errorFile = completableFuture1.join();
        try {
            if (errorFile != null) {
                consumeInterface.error(excelError);
                excelImportRes.setStatus(ResponseEnum.IMPORT_FILE_DATA_EXP.status());
                excelImportRes.setDownErrorUrl(downloadPath + workbookId);
                excelImportRes.setErrorFile(errorFile);
            } else {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream(3072);
                excelWorkbookManage.getWorkbook(workbookId).write(bytes);
            }
            excelImportRes.setMessage(String.format(ResponseEnum.IMPORT_FILE_DATA_EXP.message(), sheetDataList.get(0).getData().size(), total - sheetDataList.get(0).getData().size()));
        } catch (Exception e) {
            log.error("", e);
        }
        //释放工作空间
        excelWorkbookManage.removeWorkbook(workbookId);
        return excelImportRes;

    }

    private ErrorFile writeError(String workbookId, ExcelError excelError, Workbook workbook, String fileName, int rowStart) {
        ErrorFile errorFile = new ErrorFile(workbookId, NetworkUtil.getLocalIP(), port, tmpPath, fileName);
        executorTools.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                redisTemplate.opsForValue().set("$$poi-excel:import:" + workbookId, JSON.toJSONString(errorFile), 15, TimeUnit.DAYS);
            }
        });
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tmpPath + fileName);
            List<ExcelError.SheetError> sheetErrorList = excelError.getSheetErrors();
            sheetErrorList.forEach(sheetError -> {
                Font font = workbook.createFont();
                CellStyle cellStyle = workbook.createCellStyle();
                //设置红色
                cellStyle.setFont(font);
                font.setColor(Font.COLOR_RED);
                Sheet sheet = workbook.getSheetAt(sheetError.getIndex());
                Row head = sheet.getRow(rowStart - 1);
                int colNum = head.getLastCellNum();
                Cell headCell = head.createCell(colNum);
                headCell.setCellValue("错误信息");
                headCell.setCellStyle(cellStyle);
                List<RowError> rowErrors = sheetError.getRowErrors();
                rowErrors.forEach(rowError -> {
                    Row row = sheet.getRow(rowError.getRowIndex());
                    Cell cell = row.createCell(colNum);
                    cell.setCellValue(rowError.getErrorMsg());
                    cell.setCellStyle(cellStyle);
                });
            });
            try {
                workbook.write(fileOutputStream);
            } catch (Exception e) {
                log.error("", e);
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (IOException io) {
                    log.error("", io);
                }
            }
        } catch (Exception e1) {
            log.error("", e1);
        }
        return errorFile;
    }

    public void addErrorInfo(String workbookId, List<RowError> rowErrors) {
        excelWorkbookManage.addErrorInfo(workbookId, 0, rowErrors);

    }

    public void addErrorInfo(String workbookId, int sheetIndex, RowError rowError) {
        excelWorkbookManage.addErrorInfo(workbookId, sheetIndex, rowError);
    }

    public void addErrorInfo(String workbookId, int sheetIndex, List<RowError> rowErrors) {
        excelWorkbookManage.addErrorInfo(workbookId, sheetIndex, rowErrors);
    }
}
