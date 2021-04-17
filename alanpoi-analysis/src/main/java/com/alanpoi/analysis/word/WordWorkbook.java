package com.alanpoi.analysis.word;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class WordWorkbook implements Closeable {

    private Template template;

    private Map<String, Object> dataMap;

    public WordWorkbook(Template template) {
        this.template = template;
    }

    @Override
    public void close() throws IOException {

    }

    public void write(OutputStream stream) throws IOException, TemplateException {
        Writer out = new BufferedWriter(new OutputStreamWriter(stream, "utf-8"));
        template.process(dataMap, out);
        if (out != null) {
            out.close();
        }
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
