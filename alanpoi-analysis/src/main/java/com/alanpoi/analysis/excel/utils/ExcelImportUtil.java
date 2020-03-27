package com.alanpoi.analysis.excel.utils;

import com.alanpoi.analysis.excel.imports.AbstractFileParser;
import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.analysis.excel.imports.ExcelImportRes;
import com.alanpoi.analysis.excel.imports.ExcelSheetData;
import com.alanpoi.analysis.excel.imports.handle.RowError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Excel Import Util
 *
 * @author pengzhuoxun
 * @since 2020-3-10
 */
public class ExcelImportUtil {

    /**
     * Get Excel Data
     *
     * @param excelId
     * @param inputStream
     * @param fileName
     * @return
     */
    public static List<ExcelSheetData> getExcelData(String excelId, InputStream inputStream, String fileName) {
        return (getFileParser()).getData(excelId, inputStream, fileName);
    }

    /**
     * Custom Import Data
     * User inheritable interface `ExcelConsumeInterface` processing business logic,Can be used with methods `reportExcelError`
     *
     * @param excelId
     * @param inputStream
     * @param fileName
     * @param excelParam
     * @return
     */
    public static ExcelImportRes customImportData(String excelId, InputStream inputStream, String fileName, Map<Serializable, Object> excelParam) {
        return (getFileParser()).importData(excelId, inputStream, fileName, excelParam);
    }

    public static ExcelImportRes customImportData(String excelId, InputStream inputStream, String fileName) {
        return customImportData(excelId, inputStream, fileName, null);
    }

    private static AbstractFileParser getFileParser() {
        return (AbstractFileParser) ApplicationUtil.getBean(AbstractFileParser.class);
    }

    /**
     * Report Excel Error
     *
     * @param workbookId
     * @param sheetIndex
     * @param sheetErrors
     */
    public static void reportExcelError(String workbookId, int sheetIndex, List<RowError> sheetErrors) {
        getFileParser().addErrorInfo(workbookId, sheetIndex, sheetErrors);
    }

    public static void reportExcelError(String workbookId, List<RowError> rowErrors) {
        getFileParser().addErrorInfo(workbookId, rowErrors);
    }

    public static void reportExcelError(String workbookId, int sheetIndex, RowError rowError) {
        getFileParser().addErrorInfo(workbookId, sheetIndex, rowError);
    }

    public static void download(String fileId, HttpServletResponse response, HttpServletRequest request) {
        getFileParser().download(fileId, response, request);
    }
}
