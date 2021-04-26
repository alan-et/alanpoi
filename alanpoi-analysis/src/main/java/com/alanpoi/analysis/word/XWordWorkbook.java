package com.alanpoi.analysis.word;

import com.alanpoi.common.util.StringUtils;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class XWordWorkbook implements IWordWorkbook {

    private String tmpFileDir;

    private InputStream inputStream;

    public XWordWorkbook() {

    }

    public XWordWorkbook(String tmpFileDir) {
        this.tmpFileDir = tmpFileDir;
        try {
            inputStream = new FileInputStream(tmpFileDir);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close() throws IOException {
        File tmp = new File(tmpFileDir);
        tmp.delete();
    }

    @Override
    public void write(OutputStream stream) throws IOException {
        if (StringUtils.isBlank(tmpFileDir)) {
            throw new FileNotFoundException();
        }
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            inputStream = new FileInputStream(tmpFileDir);
            bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[10240];
            int b;
            while ((b = bufferedInputStream.read(bytes)) != -1) {
                stream.write(bytes, 0, b);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

}
