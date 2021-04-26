package com.alanpoi.analysis.common.utils;

import com.alanpoi.analysis.word.IWordWorkbook;
import com.alanpoi.analysis.word.Media;
import com.alanpoi.analysis.word.handle.WordHandle;
import com.alanpoi.common.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Excel Export Util
 *
 * @author pengzhuoxun
 * @since 2020-3-18
 */
public class WordExportUtil {
    protected static final Logger logger = LoggerFactory.getLogger(WordExportUtil.class);

    /**
     * get word workbook object (word 2003)
     *
     * @param templatePath
     * @param param
     * @return
     */

    public static IWordWorkbook getWorkbook(String templatePath, Map<String, Object> param) throws IOException {
        WordHandle exportHandle = ApplicationUtil.getBean(WordHandle.class);
        exportHandle.setWord2003();
        return exportHandle.getWorkbook(templatePath, param);
    }

    public static IWordWorkbook getWorkbook(Class<?> c, Object data) throws IOException {
        WordHandle exportHandle = ApplicationUtil.getBean(WordHandle.class);
        exportHandle.setWord2003();
        return exportHandle.getWorkbook(c, data);
    }

    /**
     * * get word workbook object (word 2007+)
     *
     * @param templatePath
     * @param param
     * @return
     * @throws IOException
     */
    public static IWordWorkbook getWorkbookByDocx(String templatePath, Map<String, Object> param, List<Media> mediaList) throws IOException {
        WordHandle exportHandle = ApplicationUtil.getBean(WordHandle.class);
        return exportHandle.getWorkbookByDocx(templatePath, param, mediaList);
    }


    /**
     * export word to the browser
     *
     * @param c
     * @param data
     * @param request
     * @param response
     * @throws IOException
     */
    public static void export(Class<?> c, Object data, HttpServletRequest request, HttpServletResponse response) throws IOException {
        IWordWorkbook workbook = getWorkbook(c, data);
        download(workbook, request, response, UUID.randomUUID().toString() + ".doc");
    }

    public static void export(String templatePath, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        IWordWorkbook workbook = getWorkbook(templatePath, param);
        download(workbook, request, response, UUID.randomUUID().toString() + ".doc");
    }


    public static void export(String templatePath, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        IWordWorkbook workbook = getWorkbook(templatePath, param);
        download(workbook, request, response, fileName);
    }


    public static void exportByDocx(String templatePath, Map<String, Object> param, List<Media> mediaList, HttpServletRequest request, HttpServletResponse response) throws IOException {
        IWordWorkbook workbook = getWorkbookByDocx(templatePath, param, mediaList);
        download(workbook, request, response, UUID.randomUUID().toString() + ".doc");
    }


    public static void exportByDocx(String templatePath, Map<String, Object> param, List<Media> mediaList, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        IWordWorkbook workbook = getWorkbookByDocx(templatePath, param, mediaList);
        download(workbook, request, response, fileName);
    }


    public static void export(Class<?> c, Object data, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        IWordWorkbook workbook = getWorkbook(c, data);
        download(workbook, request, response, fileName);
    }


    private static void download(IWordWorkbook workbookEntity, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
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
