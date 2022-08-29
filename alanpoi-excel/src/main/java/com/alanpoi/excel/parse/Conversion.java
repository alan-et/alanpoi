package com.alanpoi.excel.parse;

import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.excel.exports.RowEntity;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class Conversion {
    private static Logger logger = LoggerFactory.getLogger(Conversion.class);
    protected List<RowEntity> rowEntities;

    protected List<Object> sharedList;

    protected Document document;


    protected File sheetFile;


    public void start(BiFunction<Map<String, Object>, File, Boolean> callback) {

    }

    public void mergeCell(int beginRowIndex, int offset, int total, int interval) throws IOException {
        Element root = document.getRootElement();
        List<Element> mergeCells = root.getChildren("mergeCells", root.getNamespace());
        if (mergeCells == null || mergeCells.isEmpty()) {
            return;
        }
        Element mergeCell = mergeCells.get(0);
        int count = Integer.valueOf(mergeCell.getAttributeValue("count"));
        List<Element> mergeItems = mergeCell.getChildren();
        if (mergeItems != null && mergeItems.size() != count) {
            logger.warn("[合并单元格]模版异常，请检查");
            return;
        }
        List<String> templateList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Element item = mergeItems.get(i);
            String ref = item.getAttributeValue("ref");
            String[] val = ref.split(":");
            String[] mergeInfoAt = handleMerge(val[0]);
            String[] mergeInfoTo = handleMerge(val[1]);
            int rowAt = Integer.valueOf(mergeInfoAt[1]);
            int rowTo = Integer.valueOf(mergeInfoTo[1]);
            if (rowAt >= beginRowIndex) {
                rowAt += offset;
                rowTo += offset;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(mergeInfoAt[0])
                        .append(rowAt).append(":")
                        .append(mergeInfoTo[0])
                        .append(rowTo);
                item.setAttribute("ref", stringBuffer.toString());
                templateList.add(stringBuffer.toString());
            }
        }
        int point = 0;
        for (int i = 0; i < total - 1; i++) {
            point += interval;
            for (String ref : templateList) {
                Element newMergeCell = new Element("mergeCell", root.getNamespace());
                String[] val = ref.split(":");
                String[] mergeInfoAt = handleMerge(val[0]);
                String[] mergeInfoTo = handleMerge(val[1]);
                int rowAt = Integer.valueOf(mergeInfoAt[1]);
                int rowTo = Integer.valueOf(mergeInfoTo[1]);
                rowAt += point;
                rowTo += point;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(mergeInfoAt[0])
                        .append(rowAt).append(":")
                        .append(mergeInfoTo[0])
                        .append(rowTo);
                newMergeCell.setAttribute("ref", stringBuffer.toString());
                mergeCell.addContent(newMergeCell);
            }
        }
        XMLOutputter outPutter = new XMLOutputter();
        outPutter.output(document, new FileOutputStream(sheetFile));
    }

    protected String[] handleMerge(String val) {
        char[] chars = val.toCharArray();
        String[] str = new String[2];
        StringBuffer digit = new StringBuffer();
        StringBuffer letter = new StringBuffer();
        for (int j = 0; j < chars.length; j++) {
            if (Character.isDigit(chars[j])) {
                digit.append(chars[j]);
            } else {
                letter.append(chars[j]);
            }

        }
        str[0] = letter.toString();
        str[1] = digit.toString();
        return str;
    }
}
