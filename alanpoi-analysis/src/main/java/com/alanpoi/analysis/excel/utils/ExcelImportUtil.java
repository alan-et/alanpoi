package com.alanpoi.analysis.excel.utils;

import com.alanpoi.analysis.common.PoiEventManager;
import com.alanpoi.analysis.excel.imports.AbstractFileParser;
import com.alanpoi.common.event.Event;
import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.analysis.excel.imports.ExcelImportRes;
import com.alanpoi.analysis.excel.imports.ExcelSheetData;
import com.alanpoi.analysis.excel.imports.handle.RowError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
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
     * @return List<ExcelSheetData> excel data
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
     * @return ExcelImportRes
     */
    public static ExcelImportRes customImportData(String excelId, InputStream inputStream, String fileName, Map<Serializable, Object> excelParam) {
        return (getFileParser()).importData(excelId, inputStream, fileName, excelParam);
    }

    public static ExcelImportRes customImportData(String excelId, InputStream inputStream, String fileName) {
        return customImportData(excelId, inputStream, fileName, null);
    }

    /**
     * send async import
     *
     * @param excelId
     * @param inputStream
     * @param fileName
     * @param excelParam
     */
    public static void asyncImport(String excelId, InputStream inputStream, String fileName, Map<Serializable, Object> excelParam) {
        Event event = getImportEvent(excelId, inputStream, fileName, excelParam);
        PoiEventManager.getDispatcher().trigger(event);
    }

    public static void asyncImport(String excelId, InputStream inputStream, String fileName) {
        asyncImport(excelId, inputStream, fileName, null);
    }

    private static Event getImportEvent(String excelId, InputStream inputStream, String fileName, Map<Serializable, Object> excelParam) {
        Map<String, Object> param = new HashMap<>();
        Event event = new Event();
        param.put("excelId", excelId);
        param.put("inputStream", inputStream);
        param.put("fileName", fileName);
        param.put("excelParam", excelParam);
        event.setName(PoiEventManager.POI_IMPORT_EVENT_NAME);
        event.setData(param);
        return event;
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
