package com.alanpoi.analysis.excel.imports;

import java.io.Serializable;
import java.util.List;

/**
 * excel全局参数
 */
public class ExcelSheet implements Serializable {
    private int index = 0;
    private int headStart = 0;
    private int rowStart = 1;  //开始行
    private int colStart = 1;  //开始列

    private String[] column = null; //映射列(属性)名称集合
    private String[] excelColumn = null; //映射Excel列名称集合
    private List<ExcelColumnEntity> columnEntities;

    private Class T;

    public ExcelSheet() {

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRowStart() {
        return rowStart;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public int getColStart() {
        return colStart;
    }

    public void setColStart(int colStart) {
        this.colStart = colStart;
    }

    public String[] getColumn() {
        return column;
    }

    public void setColumn(String[] column) {
        this.column = column;
    }

    public Class getT() {
        return T;
    }

    public void setT(Class t) {
        T = t;
    }

    public List<ExcelColumnEntity> getColumnEntities() {
        return columnEntities;
    }

    public void setColumnEntities(List<ExcelColumnEntity> columnEntities) {
        this.columnEntities = columnEntities;
    }

    public int getHeadStart() {
        return headStart;
    }

    public void setHeadStart(int headStart) {
        this.headStart = headStart;
    }

    public String[] getExcelColumn() {
        return excelColumn;
    }

    public void setExcelColumn(String[] excelColumn) {
        this.excelColumn = excelColumn;
    }
}
