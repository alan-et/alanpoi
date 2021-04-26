package com.alanpoi.analysis.word;


import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * @author pengzhuoxun
 * @since 1.3.4
 */
public class DocParse extends WordParse {
    private final static Logger logger = LoggerFactory.getLogger(DocParse.class);

    public DocParse() {

        configure = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configure.setDefaultEncoding("utf-8");

    }

    /**
     * 创建word文档对象
     *
     * @param entityList
     * @param dataMap
     * @return
     * @throws IOException
     */
    public IWordWorkbook createDoc(List<WordEntity> entityList, Map<String, Object> dataMap) throws IOException {
        File ftlFile = generateFtl(entityList);
        Template template = createTemplate(ftlFile.getParent(), ftlFile.getName(), true);
        WordWorkbook workbook = new WordWorkbook(template);
        workbook.setDataMap(dataMap);
        ftlFile.delete();
        return workbook;
    }

    /**
     * 自动匹配生成模版
     *
     * @param entityList
     * @return
     * @throws IOException
     */
    public File generateFtl(List<WordEntity> entityList) throws IOException {
        String h = new String(Base64.getDecoder().decode(PdfConfig.getH()));
        String f1 = new String(Base64.getDecoder().decode(PdfConfig.getF1()));
        String f2 = new String(Base64.getDecoder().decode(PdfConfig.getF2()));
        String body = null;
        if (entityList != null && entityList.size() > 0) {
            entityList.sort(Comparator.comparing(e -> e.getIndex()));
            //parse
            body = parseEntity(entityList);
        }
        Writer writer = null;
        BufferedWriter bos = null;
        File temp = null;
        try {
            temp = File.createTempFile(UUID.randomUUID().toString(), ".ftl");
            writer = new FileWriter(temp);
            bos = new BufferedWriter(writer);
            bos.write(h);
            if (body != null) bos.write(body);
            bos.write(f1);
            bos.write(f2);
            return temp;
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            if (bos != null) bos.close();
            if (writer != null) writer.close();
        }
        throw new FileNotFoundException();
    }
}
