package com.alanpoi.word;

import com.alanpoi.word.enums.WordAlign;
import com.alanpoi.word.enums.WordHighlight;
import com.alanpoi.word.enums.WordStyle;
import com.alanpoi.common.util.StringUtils;
import com.alanpoi.xml.AbstractParse;
import com.alanpoi.xml.common.Label;
import com.alanpoi.xml.common.Media;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author pengzhuoxun
 * @since 1.3.4
 */
public abstract class WordParse extends AbstractParse {

    /**
     * 创建word文档对象
     *
     * @param entityList
     * @param dataMap
     * @return
     * @throws IOException
     */
    public IWordWorkbook createDoc(List<WordEntity> entityList, Map<String, Object> dataMap) throws IOException {
        throw new UnsupportedOperationException();
    }

    public IWordWorkbook createDoc(String zipTemplatePath, Map<String, Object> dataMap, List<Media> mediaList) throws IOException {
        throw new UnsupportedOperationException();
    }

    public IWordWorkbook createDoc(String templatePath, Map<String, Object> dataMap) throws IOException {
        File file = new File(templatePath);
        if (!file.exists()) {
            //获取项目相对路径资源
            String[] pathArr = templatePath.split("/");
            String fileName = pathArr[pathArr.length - 1];
            String parentPath = templatePath.replace(fileName, "");
            return createDoc(File.separator + parentPath, fileName, dataMap, false);
        }
        return createDoc(file, dataMap, true);
    }

    public IWordWorkbook createDoc(File templateFile, Map<String, Object> dataMap, boolean isAbsolute) throws IOException {
        return createDoc(templateFile.getParent(), templateFile.getName(), dataMap, isAbsolute);
    }

    public IWordWorkbook createDoc(String parentPath, String fileName, Map<String, Object> dataMap, boolean isAbsolute) throws IOException {
        Template template = createTemplate(parentPath, fileName, isAbsolute);
        WordWorkbook workbook = new WordWorkbook(template);
        workbook.setDataMap(dataMap);
        return workbook;
    }

    /**
     * 解析实体数据
     *
     * @param entityList
     * @return
     */
    protected String parseEntity(List<WordEntity> entityList) {
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
}
