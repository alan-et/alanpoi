package com.alanpoi.common;

public enum ExcelType {
    EXCEL_2003("EXCEL_2003"),
    EXCEL_2007("EXCEL_2003");

    private String value;

    ExcelType(String value) {
        this.value = value;
    }
}
