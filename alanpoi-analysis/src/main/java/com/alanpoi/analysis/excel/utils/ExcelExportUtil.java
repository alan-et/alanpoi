package com.alanpoi.analysis.excel.utils;

import com.alanpoi.analysis.common.ExcelType;
import com.alanpoi.analysis.excel.exports.WorkbookEntity;
import com.alanpoi.analysis.excel.exports.WorkbookManager;
import com.alanpoi.analysis.excel.exports.handle.ExportHandle;
import com.alanpoi.common.util.ApplicationUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * Excel Export Util
 *
 * @author pengzhuoxun
 * @since 2020-3-18
 */
public class ExcelExportUtil {
    protected static final Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);

    public static Workbook getWorkbook(Collection<?> singleSheetData, Class<?> c) {
        return getWorkbook(ExcelType.EXCEL_2007, singleSheetData, c);
    }

    public static Workbook getWorkbook(ExcelType excelType, Collection<?> singleSheetData, Class<?> c) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportData(WorkbookManager.newWorkbook(excelType), singleSheetData, c);
    }

    /**
     * Multi sheet export
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

    private static Workbook getWorkbookMulti(Workbook workbook, Map<Class<?>, Collection<?>> dataMap) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportMultipleSheet(workbook, dataMap);
    }


    public static void export(ExcelType excelType, Collection<?> singleSheetData, Class<?> c, HttpServletRequest request, HttpServletResponse response, String fileName) {
        WorkbookManager workbookManager = ApplicationUtil.getBean(WorkbookManager.class);
        WorkbookEntity workbookEntity = workbookManager.getWorkbookManager(excelType);
        getWorkbook(workbookEntity.getWorkbook(), singleSheetData, c);
        download(workbookEntity, request, response, fileName);
    }

    public static void export(Collection<?> singleSheetData, Class<?> c, HttpServletRequest request, HttpServletResponse response, String fileName) {
        export(ExcelType.EXCEL_2007, singleSheetData, c, request, response, fileName);
    }

    public static void export(Collection<?> singleSheetData, Class<?> c, HttpServletRequest request, HttpServletResponse response) {
        export(singleSheetData, c, request, response, UUID.randomUUID().toString() + ".xlsx");
    }

    public static void exportByMultiSheet(Map<Class<?>, Collection<?>> dataMap, HttpServletRequest request, HttpServletResponse response) {
        exportByMultiSheet(dataMap, UUID.randomUUID().toString() + ".xlsx", request, response);
    }

    public static void exportByMultiSheet(Map<Class<?>, Collection<?>> dataMap, String fileName, HttpServletRequest request, HttpServletResponse response) {
        WorkbookManager workbookManager = ApplicationUtil.getBean(WorkbookManager.class);
        WorkbookEntity workbookEntity = workbookManager.getWorkbookManager(ExcelType.EXCEL_2007, dataMap.keySet());
        getWorkbookMulti(workbookEntity.getWorkbook(), dataMap);
        download(workbookEntity, request, response, fileName);
    }

    private static void download(WorkbookEntity workbookEntity, HttpServletRequest request, HttpServletResponse response, String fileName) {
        try {
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
                logger.error(e.getMessage(), e);
                return;
            }
            workbookEntity.getWorkbook().write(response.getOutputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            workbookEntity.close();
        }

    }

}
