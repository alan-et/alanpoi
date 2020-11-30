package com.alanpoi.common.util;

import com.alanpoi.common.enums.TagName;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * http util
 *
 * @author pengzhuoxun
 * @since 1.1.2
 */
public class HttpUtils {
    static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String httpGet(String urlStr, String contentType) {
        return httpGet(urlStr, contentType, new HashMap<>());
    }

    public static String httpGet(String urlStr, Map<String, String> headerMap) {
        return httpGet(urlStr, "application/json; charset=utf-8", headerMap);
    }

    public static <T> T httpGet(String urlStr, Map<String, String> headerMap, Class<? extends T> c) {
        String result = httpGet(urlStr, "application/json; charset=utf-8", headerMap);
        if (result == null) return null;
        return JSON.parseObject(result, c);
    }

    public static String httpGet(String urlStr, String contentType, Map<String, String> headerMap) {
        BufferedReader in = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", contentType);
            Set<String> keySet = headerMap.keySet();
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String key = it.next();
                conn.setRequestProperty(key, headerMap.get(key));
            }
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
        return httpPostWithBody(urlStr, body, new HashMap<>());
    }

    public static String httpPostWithBody(String urlStr, String body, Map<String, String> headerMap) {
        BufferedReader in = null;
        OutputStream os = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            Set<String> keySet = headerMap.keySet();
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String key = it.next();
                conn.setRequestProperty(key, headerMap.get(key));
            }
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
        return httpPostWithBody(urlStr, body, new HashMap<>(), resultClass);
    }

    public static <T> T httpPostWithBody(String urlStr, String body, Map<String, String> headerMap, Class<? extends T> resultClass) {
        String result = httpPostWithBody(urlStr, body, headerMap);
        logger.info("response:{}", result);
        if (result == null) return null;
        return JSON.parseObject(result, resultClass);
    }

    /**
     * 获取页面所有内容
     *
     * @param address
     * @return
     * @throws IOException
     */
    public static String getHtml(String address) throws IOException {
        URL url = new URL(address);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        urlConnection.setUseCaches(false);
//        urlConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
        urlConnection.connect();
//        if (urlConnection.getResponseCode() == 200) {
        InputStream inputStream = urlConnection.getInputStream();

        String contentType = urlConnection.getContentType();
        String charset = getCharset(contentType);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\r\n");
        }
        return sb.toString();
//        }
//        throw new ServerException(urlConnection.getResponseMessage());
    }

    private static String getCharset(String contentType) {
        String charset = "gbk";
        if (StringUtils.isNotBlank(contentType)) {
            String[] arr = contentType.split(";");
            if (arr.length > 1) {
                String[] arr1 = arr[1].split("=");
                if ("charset".equals(arr1[0].trim())) {
                    charset = arr1[1];
                }
            }
        }
        return charset;
    }

    /**
     * 获取指定标签内容
     *
     * @param address
     * @param tagName
     * @return
     * @throws IOException
     */
    public static List<String> getTagVal(String address, TagName tagName) throws IOException {
        List<String> tagValList = new ArrayList<>();
        String html = getHtml(address);
        Pattern r = Pattern.compile(String.format("<%s.*?</%s>", tagName, tagName), Pattern.DOTALL);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(html);
        while (m.find()) {
            tagValList.add(m.group());
        }
        return tagValList;
    }

    public static String getTitle(String address) throws IOException {
        String html = getHtml(address);
        Pattern r = Pattern.compile("<title.*?</title>", Pattern.DOTALL);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(html);
        String title = "";
        if (m.find()) {
            title = m.group();
        }
        return title;
    }

    public static String getBody(String address) throws IOException {
        String html = getHtml(address);
        Pattern r = Pattern.compile("<body.*?</body>", Pattern.DOTALL);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(html);
        String body = "";
        if (m.find()) {
            body = m.group();
        }
        return body;
    }

    public static String getBodyText(String address) throws IOException {
        String body = getBody(address);
        Pattern r = Pattern.compile("<.+?>");
        Matcher m = r.matcher(body);
        String bodyText = m.replaceAll("").replaceAll("/r/n", "");
        //filter all <script>
        Pattern r1 = Pattern.compile(String.format("<%s.*?</%s>", TagName.script, TagName.script), Pattern.DOTALL);
        return r1.matcher(bodyText).replaceAll("");
    }
}
