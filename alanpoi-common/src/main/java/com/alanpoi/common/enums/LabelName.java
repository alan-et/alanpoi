package com.alanpoi.common.enums;

public enum LabelName {
    paragraph("w:p"),
    paragraphProperties("w:pPr"),
    pStyle("w:pStyle"),
    run("w:r"),
    text("w:t"),
    RunProperties("w:rPr"),
    font("w:font"),
    style("w:style");

    public String value;

    LabelName(String value) {
        this.value = value;
    }
}
