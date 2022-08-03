package com.alanpoi.analysis.common.utils;

import com.alanpoi.analysis.word.IWordWorkbook;
import com.alanpoi.analysis.word.Media;
import com.alanpoi.analysis.word.handle.WordHandle;
import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.common.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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

    public static IWordWorkbook getWorkbook(String templatePath, Map<String, Object> param, boolean isWord2003) throws IOException {
        WordHandle exportHandle = ApplicationUtil.getBean(WordHandle.class);
        if (isWord2003) {
            exportHandle.setWord2003();
        }
        return exportHandle.getWorkbook(templatePath, param);
    }

    public static IWordWorkbook getWorkbook2003(Class<?> c, Object data) throws IOException {
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
    public static IWordWorkbook getWorkbook2007(String templatePath, Map<String, Object> param, List<Media> mediaList) throws IOException {
        WordHandle exportHandle = ApplicationUtil.getBean(WordHandle.class);
        return exportHandle.getWorkbook2007(templatePath, param, mediaList);
    }

    /**
     * Word conversion Pdf（support doc or docx）
     *
     * @param workbook
     * @param pdfStream
     * @throws IOException
     */
    public static void wordToPdf(IWordWorkbook workbook, OutputStream pdfStream) throws IOException {
        WordHandle exportHandle = ApplicationUtil.getBean(WordHandle.class);
        exportHandle.wordToPDF(workbook.getInputStream(), pdfStream);
    }

    /**
     * Word conversion Pdf, Then export to browser
     *
     * @param workbook
     * @param request
     * @param response
     * @param pdfName
     * @throws IOException
     */
    public static void wordToPdfToBrowser(IWordWorkbook workbook, HttpServletRequest request, HttpServletResponse response, String pdfName) throws IOException {
        WordHandle exportHandle = ApplicationUtil.getBean(WordHandle.class);
        ResponseUtil.handleResponse(request, response, pdfName);
        exportHandle.wordToPDF(workbook.getInputStream(), response.getOutputStream());
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
    public static void export2003(Class<?> c, Object data, HttpServletRequest request, HttpServletResponse response) throws IOException {
        IWordWorkbook workbook = getWorkbook2003(c, data);
        download(workbook, request, response, UUID.randomUUID().toString() + ".doc");
    }

    public static void export2003(String templatePath, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        IWordWorkbook workbook = getWorkbook(templatePath, param, true);
        download(workbook, request, response, UUID.randomUUID().toString() + ".doc");
    }


    public static void export2003(String templatePath, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        IWordWorkbook workbook = getWorkbook(templatePath, param, true);
        download(workbook, request, response, fileName);
    }


    public static void export2007(String templatePath, Map<String, Object> param, List<Media> mediaList, HttpServletRequest request, HttpServletResponse response) throws IOException {
        IWordWorkbook workbook = getWorkbook2007(templatePath, param, mediaList);
        download(workbook, request, response, UUID.randomUUID().toString() + ".docx");
    }


    public static void export2007(String templatePath, Map<String, Object> param, List<Media> mediaList, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        IWordWorkbook workbook = getWorkbook2007(templatePath, param, mediaList);
        download(workbook, request, response, fileName);
    }


    public static void export2003(Class<?> c, Object data, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        IWordWorkbook workbook = getWorkbook2003(c, data);
        download(workbook, request, response, fileName);
    }


    private static void download(IWordWorkbook workbookEntity, HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        try {
            ResponseUtil.handleResponse(request, response, fileName);
            workbookEntity.write(response.getOutputStream());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            workbookEntity.close();
        }
    }


}
