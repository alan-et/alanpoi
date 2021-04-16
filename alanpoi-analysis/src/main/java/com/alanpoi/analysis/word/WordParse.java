package com.alanpoi.analysis.word;

import com.alanpoi.analysis.common.enums.WordAlign;
import com.alanpoi.analysis.common.enums.WordHighlight;
import com.alanpoi.analysis.common.enums.WordStyle;
import com.alanpoi.common.util.StringUtils;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * @author pengzhuoxun
 * @since 1.3.4
 */
public class WordParse {
    private final static Logger logger = LoggerFactory.getLogger(WordParse.class);

    private Configuration configure = null;

    public WordParse() {

        configure = new Configuration();
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
    public WordWorkbook createDoc(List<WordEntity> entityList, Map<String, String> dataMap) throws IOException {
        File ftlFile = generateFtl(entityList);
        Template template = createTemplate(ftlFile);
        WordWorkbook workbook = new WordWorkbook(template);
        workbook.setDataMap(dataMap);
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

        } finally {
            if (bos != null) bos.close();
            if (writer != null) writer.close();
        }
        throw new FileNotFoundException();
    }

    /**
     * 解析实体数据
     *
     * @param entityList
     * @return
     */
    private String parseEntity(List<WordEntity> entityList) {
        StringBuilder wBody = new StringBuilder();
        entityList.forEach(e -> {
            Label label = new Label("w:p", null, "");
            if (e.getpStyle() != WordStyle.NONE || e.getAlign() != WordAlign.none) {
                Label pPr = new Label("w:pPr", null, "");
                if (e.getpStyle() != WordStyle.NONE) {
                    Map pStyleMap = new TreeMap();
                    pStyleMap.put("w:val", e.getpStyle().val);
                    Label pStyle = new PStyleLabel(pStyleMap);
                    pPr.addChild(pStyle);
                }
                if (e.getAlign() != WordAlign.none) {
                    Map alignMap = new TreeMap();
                    alignMap.put("w:val", e.getAlign().name());
                    Label align = new Label("w:jc", alignMap, "");
                    pPr.addChild(align);
                }
                label.addChild(pPr);
            }
            //add name
            if (StringUtils.isNotBlank(e.getName())) {
                Label rLabel = new Label("w:r", null, "");
                Label rPrLabel = new Label("w:rPr", null, "");
                Map fontMap = new TreeMap();
                fontMap.put("w:hint", "eastAsia");
                Label fontLabel = new Label("w:rFonts", fontMap, "");
                Map tMap = new TreeMap();
                tMap.put("xml:space", "preserve");
                Label tLabel = new Label("w:t", tMap, e.getName() + ": ");
                rPrLabel.addChild(fontLabel);
                rLabel.addChild(rPrLabel);
                rLabel.addChild(tLabel);
                label.addChild(rLabel);
            }
            //add content
            Label rLabel = new Label("w:r", null, "");
            Label rPrLabel = new Label("w:rPr", null, "");
            if (StringUtils.isNotBlank(e.getColor())) {
                Map colorMap = new TreeMap();
                colorMap.put("w:val", e.getColor());
                Label colorLabel = new Label("w:color", colorMap, "");
                rPrLabel.addChild(colorLabel);
            }
            if (e.getHighlight() != WordHighlight.NONE) {
                Map hlMap = new TreeMap();
                hlMap.put("w:val", e.getHighlight().val);
                Label hlLabel = new Label("w:highlight", hlMap, "");
                rPrLabel.addChild(hlLabel);
            }
            if (e.getrStyle() != WordStyle.NONE) {
                Map rsMap = new TreeMap();
                rsMap.put("w:val", e.getrStyle().val);
                Label rsLabel = new Label("w:rStyle", rsMap, "");
                rPrLabel.addChild(rsLabel);
            }
            Map fontMap = new TreeMap();
            fontMap.put("w:hint", "eastAsia");
            Label fontLabel = new Label("w:rFonts", fontMap, "");
            Label tLabel = new Label("w:t", null, e.getValue());
            rPrLabel.addChild(fontLabel);
            rLabel.addChild(rPrLabel);
            rLabel.addChild(tLabel);
            label.addChild(rLabel);
            wBody.append(label.formatXml());
        });
        return wBody.toString();
    }

    public Template createTemplate(File temp) throws IOException {
        //加载模板文件
        //configure.setClassForTemplateLoading(this.getClass(), "/var/folders/3l/lsqzc9g9533dwtzcmjwz43280000gn/T/"); //将模板文件直接复制到src目录下
        configure.setDirectoryForTemplateLoading(new File(temp.getParent()));//模板文件在本地硬盘d
        //设置对象包装器
        configure.setObjectWrapper(new DefaultObjectWrapper());
        //设置异常处理器
        configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        //定义Template对象,注意模板类型名字与downloadType要一致
        return configure.getTemplate(temp.getName());  //文件名调用的时候可更换
    }
}
