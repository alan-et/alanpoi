package com.alanpoi.excel.common.enums;

import org.apache.poi.ss.usermodel.VerticalAlignment;

public enum VertAlign {
    TOP(VerticalAlignment.TOP),
    CENTER(VerticalAlignment.CENTER),
    BOTTOM(VerticalAlignment.BOTTOM),
    JUSTIFY(VerticalAlignment.JUSTIFY),
    DISTRIBUTED(VerticalAlignment.DISTRIBUTED);

    public VerticalAlignment val;

    VertAlign(VerticalAlignment val) {
        this.val = val;
    }
}
