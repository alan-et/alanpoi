package com.alanpoi.excel.common;

public class Hyperlink {

    private String ref;

    private String rId;

    private String display;

    private String target;

    private String targetMode = "External";

    public Hyperlink() {
    }

    public Hyperlink(String ref, String rId, String display, String target) {
        this.ref = ref;
        this.rId = rId;
        this.display = display;
        this.target = target;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetMode() {
        return targetMode;
    }

    public void setTargetMode(String targetMode) {
        this.targetMode = targetMode;
    }
}
