package com.alanpoi.common.util;

import com.alanpoi.common.enums.TagName;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

    public static <T> T httpGet(String urlStr, Map<String, String> headerMap, TypeReference<T> typeReference) {
        String result = httpGet(urlStr, "application/json; charset=utf-8", headerMap);
        if (result == null) return null;
        return JSON.parseObject(result, typeReference);
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

    public static <T> T httpGet(String urlStr, String contentType, TypeReference<T> typeReference) {
        String result = httpGet(urlStr, contentType);
        if (result == null) return null;
        return JSON.parseObject(result, typeReference);
    }

    public static String httpPost(String urlStr) throws IOException {
        return httpPostWithBody(urlStr, "");
    }

    public static String httpPostWithBody(String urlStr, String body) throws IOException {
        return httpPostWithBody(urlStr, body, new HashMap<>());
    }

    public static String httpPostWithBody(String urlStr, String body, Map<String, String> headerMap) throws IOException {
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
            throw e;
        } finally {
            try {
                if (os != null) os.close();
                if (in != null) in.close();
            } catch (Exception e1) {
                logger.error("close BufferedReader exception:", e1);
                throw e1;
            }
        }
    }

    public static <T> T httpPostWithBody(String urlStr, String body, TypeReference<T> typeReference) throws IOException {
        return httpPostWithBody(urlStr, body, new HashMap<>(), typeReference);
    }

    public static <T> T httpPostWithBody(String urlStr, String body, Map<String, String> headerMap, TypeReference<T> typeReference) throws IOException {
        String result = httpPostWithBody(urlStr, body, headerMap);
        logger.info("response:{}", result);
        if (result == null) return null;
        return JSON.parseObject(result, typeReference);
    }

    /**
     * Download from http
     *
     * @param httpUrl
     * @param outputStream
     * @return
     */
    public static boolean httpDownload(String httpUrl, OutputStream outputStream) {
        int byteread = 0;
        URL url = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            logger.error("", e1);
            return false;
        }

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();

            byte[] buffer = new byte[2048];
            while ((byteread = inStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, byteread);
            }
            return true;
        } catch (FileNotFoundException e) {
            logger.error("", e);
            return false;
        } catch (IOException e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * Upload file (applicable to requests where both request and response are files)
     *
     * @param urlStr
     * @param fileName
     * @param fileStream
     * @return
     */
    public static InputStream uploadFile(String urlStr, String fileName, InputStream fileStream) throws IOException {
        InputStream inputStream = null;
        try {
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            String BOUNDARY = "--------alanpoi7f4a64158o6";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + fileName + "\"" + newLine);
            sb.append("Content-Type:application/octet-stream");
            sb.append(newLine);
            sb.append(newLine);
            byte[] begin_data = sb.toString().getBytes();
            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY
                    + boundaryPrefix + newLine).getBytes();

            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            conn.setRequestProperty("Content-Length", String.valueOf(begin_data.length + fileStream.available() + end_data.length));

            OutputStream out = conn.getOutputStream();
            out.write(begin_data);

            DataInputStream in = new DataInputStream(fileStream);
            byte[] bufferOut = new byte[3072];
            int bytes = 0;
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            out.write(end_data);
            out.flush();
            out.close();
            inputStream = conn.getInputStream();
        } catch (Exception e) {
            throw e;
        }
        return inputStream;
    }

    public static void uploadFileToBrowser(String url, String uploadName, String downName, InputStream fileStream, HttpServletResponse response) throws IOException {
        InputStream inputStream;
        try {
            inputStream = uploadFile(url, uploadName, fileStream);
            downToBrowser(inputStream, downName, response);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void downToBrowser(InputStream inputStream, String fileName, HttpServletResponse response) throws IOException {
        try {
            ResponseUtil.handleResponse(response, fileName);
            byte[] buffer = new byte[2048];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, len);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            inputStream.close();
        }
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
