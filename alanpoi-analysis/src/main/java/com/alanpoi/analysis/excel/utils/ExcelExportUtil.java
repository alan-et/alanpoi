package com.alanpoi.analysis.excel.utils;

import com.alanpoi.analysis.common.enums.ExcelType;
import com.alanpoi.common.util.ResponseUtil;
import com.alanpoi.analysis.excel.exports.WorkbookEntity;
import com.alanpoi.analysis.excel.exports.WorkbookManager;
import com.alanpoi.analysis.excel.exports.handle.ExportHandle;
import com.alanpoi.common.util.ApplicationUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Excel Export Util
 *
 * @author pengzhuoxun
 * @since 2020-3-18
 * @deprecated 1.3.4
 */
@Deprecated
public class ExcelExportUtil {
    protected static final Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);

    /**
     * get excel workbook object
     *
     * @param singleSheetData
     * @param c
     * @return
     */
    public static Workbook getWorkbook(Collection<?> singleSheetData, Class<?> c) {
        return getWorkbook(ExcelType.EXCEL_2007, singleSheetData, c);
    }

    public static Workbook getWorkbook(ExcelType excelType, Collection<?> singleSheetData, Class<?> c) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportData(WorkbookManager.newWorkbook(excelType), singleSheetData, c);
    }

    /**
     * Gets the workbook object of the specified column
     *
     * @param singleSheetData
     * @param c
     * @param specifyCol
     * @return
     */
    public static Workbook getWorkbookSpecifyCol(Collection<?> singleSheetData, Class<?> c, List<String> specifyCol) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportData(WorkbookManager.newWorkbook(ExcelType.EXCEL_2007), singleSheetData, c, specifyCol);
    }

    /**
     * Get Multi sheet workbook object
     *
     * @param excelType
     * @param dataMap
     * @return Workbook
     */
    public static Workbook getWorkbookByMultiSheet(ExcelType excelType, Map<Class<?>, Collection<?>> dataMap) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportMultipleSheet(WorkbookManager.newWorkbook(excelType, dataMap.keySet()), dataMap);
    }

    public static Workbook getWorkbookByMultiSheet(Map<Class<?>, Collection<?>> dataMap) {
        return getWorkbookByMultiSheet(ExcelType.EXCEL_2007, dataMap);
    }

    private static Workbook getWorkbook(Workbook workbook, Collection<?> singleSheetData, Class<?> c) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportData(workbook, singleSheetData, c);
    }

    private static Workbook getWorkbookSpecifyCol(Workbook workbook, Collection<?> singleSheetData, Class<?> c, List<String> specifyCol) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportData(workbook, singleSheetData, c, specifyCol);
    }

    private static Workbook getWorkbookMulti(Workbook workbook, Map<Class<?>, Collection<?>> dataMap, Map<Integer, List<String>> specifyCol) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportMultipleSheet(workbook, dataMap, specifyCol);
    }


    public static void export(ExcelType excelType, Collection<?> singleSheetData, Class<?> c, HttpServletRequest request, HttpServletResponse response, String fileName) {
        WorkbookManager workbookManager = ApplicationUtil.getBean(WorkbookManager.class);
        WorkbookEntity workbookEntity = workbookManager.getWorkbookManager(excelType);
        getWorkbook(workbookEntity.getWorkbook(), singleSheetData, c);
        download(workbookEntity, request, response, fileName);
    }

    /**
     * Export Excel to the browser, allowing you to specify the columns to be exported
     *
     * @param excelType
     * @param singleSheetData
     * @param c
     * @param request
     * @param response
     * @param fileName
     * @param specifyCol
     */
    public static void exportSpecifyCol(ExcelType excelType,
                                        Collection<?> singleSheetData,
                                        Class<?> c,
                                        HttpServletRequest request,
                                        HttpServletResponse response,
                                        String fileName,
                                        List<String> specifyCol) {
        WorkbookManager workbookManager = ApplicationUtil.getBean(WorkbookManager.class);
        WorkbookEntity workbookEntity = workbookManager.getWorkbookManager(excelType);
        getWorkbookSpecifyCol(workbookEntity.getWorkbook(), singleSheetData, c, specifyCol);
        download(workbookEntity, request, response, fileName);
    }

    /**
     * Export Excel to the browser
     *
     * @param singleSheetData
     * @param c
     * @param request
     * @param response
     * @param fileName
     */
    public static void export(Collection<?> singleSheetData, Class<?> c, HttpServletRequest request, HttpServletResponse response, String fileName) {
        export(ExcelType.EXCEL_2007, singleSheetData, c, request, response, fileName);
    }

    public static void export(Collection<?> singleSheetData, Class<?> c, HttpServletRequest request, HttpServletResponse response) {
        export(singleSheetData, c, request, response, getDefaultFileName(ExcelType.EXCEL_2007));
    }

    public static void exportByMultiSheet(Map<Class<?>, Collection<?>> dataMap, HttpServletRequest request, HttpServletResponse response) {
        exportByMultiSheet(dataMap, getDefaultFileName(ExcelType.EXCEL_2007), request, response);
    }

    /**
     * Export multiple sheet tab Excel to the browser
     *
     * @param dataMap
     * @param fileName
     * @param request
     * @param response
     */
    public static void exportByMultiSheet(Map<Class<?>, Collection<?>> dataMap, String fileName, HttpServletRequest request, HttpServletResponse response) {
        WorkbookManager workbookManager = ApplicationUtil.getBean(WorkbookManager.class);
        WorkbookEntity workbookEntity = workbookManager.getWorkbookManager(ExcelType.EXCEL_2007, dataMap.keySet());
        getWorkbookMulti(workbookEntity.getWorkbook(), dataMap, new HashMap<>());
        download(workbookEntity, request, response, fileName);
    }

    /**
     * Export multiple sheet tab Excel to the browser, allowing you to specify the columns to be exported
     *
     * @param dataMap
     * @param fileName
     * @param request
     * @param response
     */
    public static void exportByMultiSheet(Map<Class<?>, Collection<?>> dataMap, String fileName, Map<Integer, List<String>> specifyCol, HttpServletRequest request, HttpServletResponse response) {
        WorkbookManager workbookManager = ApplicationUtil.getBean(WorkbookManager.class);
        WorkbookEntity workbookEntity = workbookManager.getWorkbookManager(ExcelType.EXCEL_2007, dataMap.keySet());
        getWorkbookMulti(workbookEntity.getWorkbook(), dataMap, specifyCol);
        download(workbookEntity, request, response, fileName);
    }

    /**
     * Get default file name
     *
     * @param excelType
     * @return
     */
    private static String getDefaultFileName(ExcelType excelType) {
        String uuid = UUID.randomUUID().toString();
        return excelType == ExcelType.EXCEL_2003 ? uuid + ".xls" : uuid + ".xlsx";
    }


    private static void download(WorkbookEntity workbookEntity, HttpServletRequest request, HttpServletResponse response, String fileName) {
        try {
            ResponseUtil.handleResponse(request, response, fileName);
            workbookEntity.getWorkbook().write(response.getOutputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            workbookEntity.close();
        }

    }

}
