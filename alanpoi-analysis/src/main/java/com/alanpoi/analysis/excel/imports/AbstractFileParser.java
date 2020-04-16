package com.alanpoi.analysis.excel.imports;

import com.alanpoi.common.enums.ResponseEnum;
import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.common.util.ApplicationUtil;
import com.alibaba.fastjson.JSONObject;
import com.alanpoi.analysis.excel.imports.handle.ExcelHandle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author zhuoxun.peng
 * @since 2020-2-25
 */
@Slf4j
public abstract class AbstractFileParser<T> extends ExcelHandle {
    protected ExcelInitConfig excelInitConfig;

    public AbstractFileParser() {

    }

    /**
     * 初始化解析类
     *
     * @param fileName
     */
    private Workbook initWorkbook(InputStream inputStream, String fileName) {
        int version = (fileName.endsWith(".xls") ? 2003 : 2007);
        try {
            if (version == 2003) {
                return new HSSFWorkbook(inputStream);
            } else {
                return new XSSFWorkbook(inputStream);
            }
        } catch (IOException ioe) {
            log.error("", ioe);
        }
        return null;
    }

    public List<ExcelSheetData> getData(String excelId, InputStream inputStream, String fileName) {
        List<ExcelSheetData> sheetDataList = new ArrayList<>();
        Workbook wb = initWorkbook(inputStream, fileName);
        Excel excel = excelInitConfig.getExcelConfig(excelId);

        excel.setFileName(fileName);
        List<ExcelSheet> scList = excel.getExcelSheets();
        scList.forEach(sc -> {
            //取得  预设文件中指定sheet页
            Sheet sheet = wb.getSheetAt(sc.getIndex());
            ExcelSheetData sheetData = new ExcelSheetData();
            sheetData.setIndex(sc.getIndex());
            sheetData.setRowStart(sc.getRowStart());
            sheetData.setSheetName(sheet.getSheetName());
            sheetData.setData(parse(sc, sheet));
            sheetDataList.add(sheetData);
        });
        return sheetDataList;
    }

    public void download(String fileId, HttpServletResponse response, HttpServletRequest request) {
        String errorStr = redisTemplate.opsForValue().get("$$poi-excel:import:" + fileId);
        if (StringUtils.isEmpty(errorStr)) {
            return;
        }
        ErrorFile errorFile = JSONObject.parseObject(errorStr, ErrorFile.class);
        String fileName = errorFile.getFileName();
        String filepath = errorFile.getFilePath();
        InputStream in = null;
        CloseableHttpClient httpClient = null;
        if (errorFile.getIpAddress().equals(ApplicationUtil.getInetAddress().getHostAddress())) {
            File f = new File(filepath + fileName);
            try {
                in = new FileInputStream(f);
                if (!f.exists()) {
                    response.sendError(404, "File not found!");
                    return;
                }
            } catch (IOException e) {
                log.error("", e);
            }
        } else {

            try {
                //文件不在档期服务器，获取指定服务器文件
                httpClient = HttpClientBuilder.create().build();
                HttpGet httpGet = new HttpGet("http://" + errorFile.getIpAddress() + ":" + port + request.getRequestURI());
                CloseableHttpResponse closeableHttpResponse = null;
                closeableHttpResponse = httpClient.execute(httpGet);
                HttpEntity responseEntity = closeableHttpResponse.getEntity();
                log.info("request address:{},response status：{}", null, closeableHttpResponse.getStatusLine());
                in = responseEntity.getContent();
            } catch (Exception e) {
                log.warn("request remote server error:{}", e);
                return;
            } finally {

            }
        }

        response.setContentType("application/force-download;charset=UTF-8");
        final String userAgent = request.getHeader("USER-AGENT");
        try {
            if (userAgent.contains("MSIE") || userAgent.contains("Edge")) {// IE浏览器
                fileName = URLEncoder.encode(fileName, "UTF8");
            } else if (userAgent.contains("Mozilla")) {// google,火狐浏览器
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
            }
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            return;
        }

        OutputStream out = null;
        try {
            int len = 0;
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return;
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
                if (httpClient != null) httpClient.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private ExcelImportRes consumeHandle(String workbookId, Excel excel, List<ExcelSheetData> sheetDataList) {
        try {
            ExcelImportRes res = process(workbookId, sheetDataList, excel);
            return res;
        } catch (Exception e) {
            log.error("consumeHandle exception:", e);
            ExcelImportRes res = new ExcelImportRes();
            res.setStatus(ResponseEnum.IMPORT_BUSINESS_ERROR_EXP.status());
            res.setMessage(ResponseEnum.IMPORT_BUSINESS_ERROR_EXP.message());
            return res;
        }
    }


    /**
     * 解析多sheet页签
     *
     * @param excelId
     * @param inputStream
     * @param fileName
     * @return
     */
    public ExcelImportRes importData(String excelId, InputStream inputStream, String fileName) {
        return importData(excelId, inputStream, fileName, new HashMap<>());
    }

    public ExcelImportRes importData(String excelId, InputStream inputStream, String fileName, Map<Serializable, Object> excelParam) {
        List<ExcelSheetData> sheetDataList = new ArrayList<>();
        Workbook wb = initWorkbook(inputStream, fileName);
        Excel excel = excelInitConfig.getExcelConfig(excelId);

        excel.setFileName(fileName);
        excel.setCustomParam(excelParam);
        List<ExcelSheet> scList = excel.getExcelSheets();
        scList.forEach(sc -> {
            //取得  预设文件中指定sheet页
            Sheet sheet = wb.getSheetAt(sc.getIndex());
            ExcelSheetData sheetData = new ExcelSheetData();
            sheetData.setIndex(sc.getIndex());
            sheetData.setRowStart(sc.getRowStart());
            sheetData.setSheetName(sheet.getSheetName());
            sheetData.setData(parse(sc, sheet));
            sheetDataList.add(sheetData);
        });
        String workbookId = UUID.randomUUID().toString();
        excelWorkbookManage.addWorkbook(workbookId, wb);
        return consumeHandle(workbookId, excel, sheetDataList);
    }

    private List parse(ExcelSheet sc, Sheet sheet) {

        ArrayList list = new ArrayList();
        int rows = sheet.getPhysicalNumberOfRows();//取得所有行

        if (log.isDebugEnabled()) {
            log.debug("\trows=" + rows);
        }

        try {
            Row headRow = sheet.getRow(sc.getHeadStart());
            List<String> excelColList = Arrays.asList(sc.getExcelColumn());
            if (sc.getExcelColumn().length > 0) {
                try {
                    excelColList.removeAll(Collections.singleton(null));
                } catch (UnsupportedOperationException e) {
                    log.warn("" + e);
                }
            } else {
                excelColList = new ArrayList<>();
            }

            if (excelColList.size() == sc.getColumn().length) {
                for (short i = (short) sc.getColStart(); i < excelColList.size() + sc.getColStart(); i++) {
                    int index = sc.getColumnEntities().indexOf(sc.getColumn()[i].trim());
                    if (index != -1) sc.getColumnEntities().get(index).setName(excelColList.get(i));
                }
            }
            for (short i = (short) sc.getColStart(); i < headRow.getPhysicalNumberOfCells(); i++) {
                int index = sc.getColumnEntities().indexOf(headRow.getCell(i).getStringCellValue().trim());
                if (index != -1) {
                    sc.getColumnEntities().get(index).setIndex(i);
                } else {
                    if (excelColList.size() == 0) {
                        index = sc.getColumnEntities().indexOf(sc.getColumn()[i].trim());
                        if (index != -1) sc.getColumnEntities().get(index).setIndex(i);
                    }
                }
            }

            for (int j = sc.getRowStart(); j < rows; j++) {
                if (null == sheet.getRow(j)) {
                    log.warn("excel row({}) is null,skip current row", j);
                    continue;
                }
                T view = getData(sc, sheet.getRow(j));
                list.add(view);
            }
        } catch (Exception e) {
            log.error("", e);
            throw new AlanPoiException(ResponseEnum.IMPORT_TEMP_EXP);
        } finally {

        }
        return list;
    }

    public boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    //解析返回对象
    abstract protected T getData(ExcelSheet sheet, Row row) throws Exception;
}
