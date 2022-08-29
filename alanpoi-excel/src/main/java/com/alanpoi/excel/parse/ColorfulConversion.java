package com.alanpoi.excel.parse;

import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.common.util.NumberUtils;
import com.alanpoi.common.util.StringUtils;
import com.alanpoi.excel.common.LetterUtils;
import com.alanpoi.excel.exports.RowEntity;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

public class ColorfulConversion extends Conversion {

    public ColorfulConversion(List<RowEntity> rowEntities,
                              Document document,
                              List<Object> sharedList,
                              File sheetFile) {
        this.rowEntities = rowEntities;
        this.document = document;
        this.sharedList = sharedList;
        this.sheetFile = sheetFile;
    }

    @Override
    public void start(BiFunction<Map<String, Object>, File, Boolean> callback) throws AlanPoiException {
        try {
            Element root = document.getRootElement();
            Element sheet = root.getChildren("sheetData", root.getNamespace()).get(0);
            List<Element> rows = sheet.getChildren("row", sheet.getNamespace());
            int step = 0;
            List<Element> tempRowList = new ArrayList<>();
            List<Element> delRowList = new ArrayList<>();
            int rIndex = 0;
            for (Element row : rows) {
                String value = "";
                for (Element c : row.getChildren("c", row.getNamespace())) {
                    value = c.getValue();
                    boolean bool = NumberUtils.isInteger(value);
                    String type = c.getAttributeValue("t");
                    String relVal = bool && "s".equals(type) ? (String) sharedList.get(Integer.valueOf(value)) : "";
                    if (StringUtils.isNotBlank(relVal)) {
                        if (relVal.trim().contains("<#list")) {
                            rIndex = Integer.valueOf(row.getAttributeValue("r"));
                            step = 1;
                            break;
                        }
                        if (relVal.trim().startsWith("</#list")) {
                            step = 9;
                            break;
                        }
                    }

                }
                if (step == 2) {
                    delRowList.add(row);
                    tempRowList.add(row);
                }
                //end
                if (step == 9) {
                    delRowList.add(row);
                    break;
                }
                if (step == 1) {
                    //遇到标识，准备写入模版
                    step += 1;
                    delRowList.add(row);
                }


            }
            delRowList.forEach(e -> {
                sheet.removeContent(e);
            });
            delRowList.clear();
            mergeCell(rIndex, -1, rowEntities.size(), tempRowList.size());
//            rIndex++;
            for (int i = 0; i < rowEntities.size(); i++) {
                RowEntity rowEntity = rowEntities.get(i);
                for (int j = 0; j < tempRowList.size(); j++) {
                    Element row = tempRowList.get(j).clone();
                    row.setAttribute("r", String.valueOf(rIndex));
                    int finalInrIndex = rIndex;
                    AtomicInteger colIndex = new AtomicInteger();
                    row.getChildren("c", row.getNamespace()).forEach(e -> {
                        String type = e.getAttributeValue("t");
                        e.setAttribute("r", LetterUtils.getColLetter(colIndex.getAndIncrement()) + finalInrIndex);
                        String text = e.getValue();
                        if ("s".equals(type) && StringUtils.isNotBlank(text)) {
                            String val = (String) sharedList.get(Integer.valueOf(text));
                            if (StringUtils.isNotBlank(val) && val.startsWith("${")) {
                                val = val.replace("${", "").replace("}", "");
                                int index = rowEntity.getCols().indexOf(val);
                                if (index != -1) {
                                    int position = rowEntity.getCols().get(index).getPosition();
                                    if (position == -1) {
                                        e.getChildren("v", e.getNamespace()).get(0).setText("");
                                    } else {
                                        e.getChildren("v", e.getNamespace()).get(0).setText(String.valueOf(position));
                                    }

                                }
                            }
                        }

                    });
                    sheet.addContent(row);
                    rIndex++;
                }
            }
            XMLOutputter outPutter = new XMLOutputter();
            outPutter.output(document, new FileOutputStream(sheetFile));
        } catch (Exception e) {
            throw new AlanPoiException(e);
        }
    }
}
