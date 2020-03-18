package com.alanpoi.excel.utils;

import com.alanpoi.common.ExcelType;
import com.alanpoi.excel.exports.WorkbookManager;
import com.alanpoi.excel.exports.handle.ExportHandle;
import com.alanpoi.excel.imports.ApplicationUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ExcelExportUtil {
    protected static final Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);

    public static Workbook getWorkbook(Collection<?> singleSheetData, Class<?> c) {
        return getWorkbook(ExcelType.EXCEL_2007, singleSheetData, c);
    }

    public static Workbook getWorkbook(ExcelType excelType, Collection<?> singleSheetData, Class<?> c) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportData(WorkbookManager.newWorkbook(excelType), singleSheetData, c);
    }

    public static Workbook getWorkbook(Workbook workbook, Collection<?> singleSheetData, Class<?> c) {
        ExportHandle exportHandle = ApplicationUtil.getBean(ExportHandle.class);
        return exportHandle.exportData(workbook, singleSheetData, c);
    }

    /**
     * @param multipleSheetData sheet name and sheet Data
     * @param headParam
     * @return
     */
    public static Workbook getWorkbook(Map<String, List<JSONObject>> multipleSheetData, Map<String, JSONObject> headParam) {
        return null;
    }

    public static void export(Collection<?> singleSheetData, Class<?> c, HttpServletRequest request, HttpServletResponse response, String fileName) {
        WorkbookManager workbookManager = ApplicationUtil.getBean(WorkbookManager.class);
        getWorkbook(workbookManager.getWorkbook(), singleSheetData, c);
        download(workbookManager, request, response, fileName);
    }

    public static void export(Collection<?> singleSheetData, Class<?> c, HttpServletRequest request, HttpServletResponse response) {
        export(singleSheetData, c, request, response, UUID.randomUUID().toString() + ".xlsx");
    }

    private static void download(WorkbookManager workbookManager, HttpServletRequest request, HttpServletResponse response, String fileName) {
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
            workbookManager.getWorkbook().write(response.getOutputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            workbookManager.close(workbookManager.getWorkbookId());
        }

    }

}
