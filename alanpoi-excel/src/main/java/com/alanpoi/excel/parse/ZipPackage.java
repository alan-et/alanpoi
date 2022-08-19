package com.alanpoi.excel.parse;

import com.alanpoi.common.util.FileUtils;
import com.alanpoi.common.util.ID;
import com.alanpoi.xml.AbstractParse;
import freemarker.template.Configuration;


import javax.annotation.PreDestroy;
import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ZipPackage extends AbstractParse {

    private String path;

    private String type;

    public String zipPath;

    public File inFile;

    public static Map<String, File> zipItem = new ConcurrentHashMap<>();


    public ZipPackage(String path, String type) throws IOException {
        this.path = path;
        this.type = type;
        configure = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configure.setDefaultEncoding("utf-8");
        init();
    }

    public File getEntity(String key) {
        return zipItem.get(key);
    }

    private void init() throws IOException {
        zipPath = FileUtils.getTmpDir() + ID.getId().next() + ".zip";
        inFile = new File(path);
        if (!inFile.exists()) {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            inFile = FileUtils.saveTempFile(is, type, ".zip", FileUtils.getTmpDir());
        }
    }

    public void complete() throws IOException {
        throw new UnsupportedOperationException();
    }

    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(zipPath);
    }

    public OutputStream getOutputStream() throws FileNotFoundException {
        return new FileOutputStream(zipPath);
    }

    public void write(OutputStream outputStream) throws IOException {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(getInputStream());
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (outputStream != null) outputStream.close();
                if (bis != null) bis.close();
                close();
            } catch (IOException e) {
                throw e;
            }
        }
    }

    public void close() {
        File file = new File(zipPath);
        if (file.exists()) {
            file.delete();
        }
        zipItem.forEach((k, v) -> {
            if (v.exists()) {
                v.delete();
            }
        });
    }

    @PreDestroy
    public void destroy() {
        close();
    }
}
