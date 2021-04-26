package com.alanpoi.analysis.word;

import com.alanpoi.common.exception.AlanPoiException;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class WordWorkbook implements IWordWorkbook {

    private Template template;

    private Map<String, Object> dataMap;

    public WordWorkbook(Template template) {
        this.template = template;
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void write(OutputStream stream) throws IOException {
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(stream, "utf-8"));
            template.process(dataMap, out);
        } catch (IOException e) {
            throw e;
        } catch (TemplateException e1) {
            throw new AlanPoiException(e1.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
