package com.alanpoi.common.enums;

public enum SimilarPrecision {

    ONE_DECIMAL("%.1f", "精度保留1位小数"),
    TWO_DECIMAL("%.2f", "精度保留2位小数"),
    THREE_DECIMAL("%.3f", "精度保留3位小数"),
    FOUR_DECIMAL("%.4f", "精度保留4位小数"),
    FIVE_DECIMAL("%.5f", "精度保留5位小数"),
    SIX_DECIMAL("%.6f", "精度保留6位小数");

    SimilarPrecision(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    public String val;
    public String desc;
}
