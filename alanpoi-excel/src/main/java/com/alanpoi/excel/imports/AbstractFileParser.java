package com.alanpoi.excel.imports;

import com.alanpoi.common.enums.ResponseEnum;
import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.excel.imports.handle.ExcelHandle;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
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


    private ExcelImportRes consumeHandle(String workbookId, Excel excel, List<ExcelSheetData> sheetDataList) {
        try {
            ExcelImportRes res = process(workbookId, sheetDataList, excel);
            return res;
        } catch (AlanPoiException e1) {
            log.error("consumeHandle exception:", e1);
            ExcelImportRes res = new ExcelImportRes();
            res.setStatus(e1.getCode());
            res.setMessage(e1.getMessage());
            return res;
        } catch (Exception e2) {
            log.error("consumeHandle exception:", e2);
            throw e2;
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
                        if (i >= sc.getColumn().length) break;
                        index = sc.getColumnEntities().indexOf(sc.getColumn()[i].trim());
                        if (index != -1) sc.getColumnEntities().get(index).setIndex(i);
                    }
                }
            }

            for (int j = sc.getRowStart(); j < rows; j++) {
                if (null == sheet.getRow(j) || isRowEmpty(sheet.getRow(j))) {
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
