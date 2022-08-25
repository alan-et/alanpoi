package com.alanpoi.excel.parse;

import com.alanpoi.excel.common.LetterUtils;
import com.alanpoi.excel.exports.RowEntity;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.util.List;

public class WorkbookBuild {

    private List<RowEntity> rowEntities;

    private List<Attribute> rowAttributes;

    private List<Attribute> colAttributes;

    public WorkbookBuild(List<RowEntity> rowEntities, Element rowElement) {
        this.rowEntities = rowEntities;
        rowAttributes = rowElement.getAttributes();
        Element colElement = rowElement.getChildren("c", rowElement.getNamespace()).get(0);
        colAttributes = colElement.getAttributes();

    }

    public String buildSheet() {
        StringBuffer result = new StringBuffer();
        rowEntities.forEach(e -> {
            result.append(buildRow(e));
        });
        return result.toString();
    }

    private String buildRow(RowEntity entity) {
        StringBuilder rowSb = new StringBuilder("<row ");
        rowAttributes.forEach(e -> {
            rowSb.append(e.getName()).append("=\"");
            if (e.getName().equals("r")) {
                rowSb.append(entity.getRowIndex()+1).append("\" ");
            } else {
                rowSb.append(e.getValue()).append("\" ");
            }
        });
        rowSb.append(">");
        List<RowEntity.ColEntity> colList = entity.getCols();
        for (int i = 0; i < colList.size(); i++) {
            StringBuilder colSb = new StringBuilder("<c ");
            int finalI = i;
            colAttributes.forEach(e -> {
                if (e.getName().equals("r")) {
                    colSb.append(e.getName()).append("=\"");
                    colSb.append(LetterUtils.getColLetter(finalI) + (entity.getRowIndex() + 1)).append("\" ");
                } else {
                    if (e.getName().equals("t")) {
                        if (colList.get(finalI).getType().equals("s")) {
                            colSb.append(e.getName()).append("=\"");
                            colSb.append(e.getValue()).append("\" ");
                        }
                    } else {
                        colSb.append(e.getName()).append("=\"");
                        colSb.append(e.getValue()).append("\" ");
                    }

                }

            });
            colSb.append(">");
            colSb.append("<v>");
            if (colList.get(i).getType().equals("n")) {
                colSb.append(colList.get(i).getValue()).append("</v>");
            } else {
                int position = colList.get(i).getPosition();
                if (position == -1) {
                    colSb.append("</v>");
                } else {
                    colSb.append(position).append("</v>");
                }

            }
            colSb.append("</c>");
            rowSb.append(colSb);
        }
        rowSb.append("</row>");
        return rowSb.toString();
    }
}
