package com.alanpoi.common.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * http util
 * @author pengzhuoxun
 * @since 1.1.2
 */
public class HttpUtils {
    static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String httpGet(String urlStr, String contentType) {
        BufferedReader in = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", contentType);
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuffer buf = new StringBuffer();
            String inputLine = in.readLine();
            while (inputLine != null) {
                buf.append(inputLine).append("\r\n");
                inputLine = in.readLine();
            }
            in.close();
            return buf.toString().trim();
        } catch (Exception e) {
            logger.error("request url:{} fail,message:", urlStr, e);
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception e1) {
                logger.error("close BufferedReader exception:", e1);
            }
        }
        return null;
    }

    public static String httpGet(String urlStr) {
        return httpGet(urlStr, "application/json; charset=utf-8");
    }

    public static <T> T httpGet(String urlStr, Class<? extends T> c) {
        String result = httpGet(urlStr, "application/json; charset=utf-8");
        if (result == null) return null;
        return JSON.parseObject(result, c);
    }

    public static <T> T httpGet(String urlStr, String contentType, Class<? extends T> c) {
        String result = httpGet(urlStr, contentType);
        if (result == null) return null;
        return JSON.parseObject(result, c);
    }

    public static String httpPost(String urlStr) {
        return httpPostWithBody(urlStr, "");
    }


    public static String httpPostWithBody(String urlStr, String body) {
        BufferedReader in = null;
        OutputStream os = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            byte[] bytes = body.getBytes("utf-8");
            os = conn.getOutputStream();
            os.write(bytes);
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer buf = new StringBuffer();
            String inputLine = in.readLine();
            while (inputLine != null) {
                buf.append(inputLine).append("\r\n");
                //
                inputLine = in.readLine();
            }
            return buf.toString().trim();
        } catch (Exception e) {
            logger.error("request {} fail,message:", urlStr, e);
        } finally {
            try {
                if (os != null) os.close();
                if (in != null) in.close();
            } catch (Exception e1) {
                logger.error("close BufferedReader exception:", e1);
            }
        }
        return null;
    }

    public static <T> T httpPostWithBody(String urlStr, String body, Class<? extends T> resultClass) {
        String result = httpPostWithBody(urlStr, body);
        if (result == null) return null;
        return JSON.parseObject(result, resultClass);
    }
}
