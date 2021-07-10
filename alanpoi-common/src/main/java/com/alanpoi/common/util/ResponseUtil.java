package com.alanpoi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * HttpResponse Util
 *
 * @since 1.3.4
 */
public class ResponseUtil {

    protected static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * Set the download properties to achieve the purpose of downloading
     *
     * @param request
     * @param response
     * @param fileName
     */
    public static void handleResponse(HttpServletRequest request, HttpServletResponse response, String fileName) {
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
    }

    /**
     * Set the download properties to achieve the purpose of downloading
     *
     * @param response
     * @param fileName
     */
    public static void handleResponse(HttpServletResponse response, String fileName) {
        response.setContentType("application/force-download;charset=UTF-8");
        try {
            fileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            return;
        }
    }
}
