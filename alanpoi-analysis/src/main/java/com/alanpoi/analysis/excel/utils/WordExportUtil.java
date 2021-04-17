package com.alanpoi.analysis.excel.utils;

import com.alanpoi.analysis.common.enums.ExcelType;
import com.alanpoi.analysis.excel.exports.WorkbookEntity;
import com.alanpoi.analysis.excel.exports.WorkbookManager;
import com.alanpoi.analysis.excel.exports.handle.ExportHandle;
import com.alanpoi.analysis.word.WordWorkbook;
import com.alanpoi.analysis.word.handle.WordHandle;
import com.alanpoi.common.util.ApplicationUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Excel Export Util
 *
 * @author pengzhuoxun
 * @since 2020-3-18
 */
public class WordExportUtil {
    protected static final Logger logger = LoggerFactory.getLogger(WordExportUtil.class);

    /**
     * get word workbook object
     *
     * @param templatePath
     * @param param
     * @return
     */

    public static WordWorkbook getWorkbook(String templatePath, Map<String, Object> param) throws IOException {
        WordHandle exportHandle = ApplicationUtil.getBean(WordHandle.class);
        return exportHandle.getWorkbook(templatePath, param);
    }

    public static WordWorkbook getWorkbook(Class<?> c, Object data) throws IOException {
        WordHandle exportHandle = ApplicationUtil.getBean(WordHandle.class);
        return exportHandle.getWorkbook(c, data);
    }


    /**
     * export word to the browser
     * @param c
     * @param data
     * @param request
     * @param response
     * @throws IOException
     */
    public static void export(Class<?> c, Object data, HttpServletRequest request, HttpServletResponse response) throws IOException {
        WordWorkbook workbook = getWorkbook(c, data);
        download(workbook, request, response, UUID.randomUUID().toString() + ".doc");
    }

    public static void export(String templatePath, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        WordWorkbook workbook = getWorkbook(templatePath, param);
        download(workbook, request, response, UUID.randomUUID().toString() + ".doc");
    }


    public static void export(String templatePath, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        WordWorkbook workbook = getWorkbook(templatePath, param);
        download(workbook, request, response, fileName);
    }


    public static void export(Class<?> c, Object data, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        WordWorkbook workbook = getWorkbook(c, data);
        download(workbook, request, response, fileName);
    }


    private static void download(WordWorkbook workbookEntity, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
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
            workbookEntity.write(response.getOutputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            workbookEntity.close();
        }

    }

}
