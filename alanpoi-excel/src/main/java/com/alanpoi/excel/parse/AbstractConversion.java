package com.alanpoi.excel.parse;

import com.alanpoi.common.util.StringUtils;
import com.alanpoi.excel.common.Hyperlink;
import com.alanpoi.excel.exports.RowEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.jdom2.*;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;

public abstract class AbstractConversion {
    private static Logger logger = LoggerFactory.getLogger(AbstractConversion.class);
    protected List<RowEntity> rowEntities;

    protected List<Object> sharedList;

    protected Document document;

    protected File sheetFile;

    private List<Hyperlink> hyperlinkList = new ArrayList<>();

    private static String NAMESPACE_RELATIONSHIPS = "http://schemas.openxmlformats.org/officeDocument/2006/relationships";
    private static String NAMESPACE_XR = "http://schemas.microsoft.com/office/spreadsheetml/2014/revision";

    public List<Hyperlink> getHyperlinkList() {
        return hyperlinkList;
    }

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

    protected void cellValFormat(Element cell, RowEntity rowEntity, String cellVal, int interval) {
        cellVal = cellVal.replace("${", "").replace("}", "");
        if (cellVal.contains("?")) {
            String[] array = cellVal.split("\\?");
            String property = array[0];
            String[] type = getType(array[1]);
            switch (type[0]) {
                case "formula":
                    String fStr = handleFormula(property, interval);
                    cell.removeAttribute("t");
                    Element f = new Element("f", cell.getNamespace());
                    f.setText(fStr);
                    cell.removeContent(0);
                    cell.addContent(f);
                    break;
                case "link":
                    Element root = document.getRootElement();
                    List<Element> list = root.getChildren("hyperlinks", root.getNamespace());
                    Element hyperlinks = null;
                    if (list == null || list.isEmpty()) {
                        hyperlinks = new Element("hyperlinks", root.getNamespace());
                        root.addContent(hyperlinks);
                    } else {
                        hyperlinks = list.get(0);
                    }
                    String displayVal = setValue(cell, rowEntity, property);
                    String linkVal = displayVal;
                    if (StringUtils.isNotBlank(type[1])) {
                        int index = rowEntity.getCols().indexOf(type[1]);
                        if (index != -1) {
                            RowEntity.ColEntity colEntity = rowEntity.getCols().get(index);
                            linkVal = String.valueOf(colEntity.getValue());
                        }
                    }
                    String cellR = cell.getAttributeValue("r");
                    Element link = new Element("hyperlink", root.getNamespace());
                    String rid = "cusrid" + cellR;
                    link.setAttribute("uid", "{" + UUID.randomUUID().toString() + "}", Namespace.getNamespace("xr", NAMESPACE_XR));
                    link.setAttribute("ref", cellR);
                    link.setAttribute("id", rid, Namespace.getNamespace("r", NAMESPACE_RELATIONSHIPS));
                    link.setAttribute("display", displayVal);
                    hyperlinks.addContent(link);
                    Hyperlink hyperlink = new Hyperlink(cellR, rid, displayVal, linkVal);
                    hyperlinkList.add(hyperlink);
                    break;
                case "image":
                    break;
                default:
                    break;
            }
        } else {
            setValue(cell, rowEntity, cellVal);
        }
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

    protected String handleFormula(String val, int position) {
        char[] chars = val.toCharArray();
        StringBuffer digit = new StringBuffer();
        StringBuffer result = new StringBuffer();
        int flag = 0;
        for (int j = 0; j < chars.length; j++) {
            if (Character.isDigit(chars[j])) {
                if (flag == 1) {
                    digit.append(chars[j]);
                } else {
                    result.append(chars[j]);
                }

            } else if (Character.isUpperCase(chars[j]) || Character.isLowerCase(chars[j])) {
                result.append(chars[j]);
                flag = 1;
            } else {
                flag = 2;
                if (digit.length() > 0) {
                    result.append(Integer.parseInt(digit.toString()) + position);
                    digit.delete(0, digit.length());
                }
                result.append(chars[j]);
            }

        }
        if (digit.length() > 0) {
            result.append(Integer.parseInt(digit.toString()) + position);
        }
        digit = null;
        return result.toString();
    }

    private String[] getType(String val) {
        if (val.contains("[")) {
            val = val.substring(0, val.length() - 1);
            return val.split("\\[");

        }
        return new String[]{val, null};
    }

    private String setValue(Element cell, RowEntity rowEntity, String cellVal) {
        String val = "";
        int index = rowEntity.getCols().indexOf(cellVal);
        if (index != -1) {
            RowEntity.ColEntity colEntity = rowEntity.getCols().get(index);
            if (ObjectUtils.isNotEmpty(colEntity.getValue())) {
                val = String.valueOf(colEntity.getValue());
            }
            if (colEntity.getPosition() == -1) {
                cell.removeAttribute("t");
                cell.getChildren("v", cell.getNamespace()).get(0).setText(val);
            } else {
                cell.getChildren("v", cell.getNamespace()).get(0).setText(String.valueOf(colEntity.getPosition()));
            }
        }
        return val;
    }
}
