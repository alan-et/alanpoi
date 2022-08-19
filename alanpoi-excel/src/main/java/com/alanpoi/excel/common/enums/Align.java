package com.alanpoi.excel.common.enums;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

public enum Align {
    GENERAL(HorizontalAlignment.GENERAL),
    LEFT(HorizontalAlignment.LEFT),
    CENTER(HorizontalAlignment.CENTER),
    RIGHT(HorizontalAlignment.RIGHT),
    FILL(HorizontalAlignment.FILL),
    JUSTIFY(HorizontalAlignment.JUSTIFY),
    CENTER_SELECTION(HorizontalAlignment.CENTER_SELECTION),
    DISTRIBUTED(HorizontalAlignment.DISTRIBUTED);

    public HorizontalAlignment val;

    Align(HorizontalAlignment val) {
        this.val = val;
    }
}
