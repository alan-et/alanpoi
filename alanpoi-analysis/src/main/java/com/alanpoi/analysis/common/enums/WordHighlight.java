package com.alanpoi.analysis.common.enums;

public enum WordHighlight {
    NONE(null),
    YELLOW("yellow"),
    GREEN("green"),
    CYAN("cyan"),
    MAGENTA("magenta"),
    BLUE("blue"),
    RED("red"),
    DARKBLUE("darkBlue"),
    DARKCYAN("darkCyan"),
    DARKGREEN("darkGreen"),
    DARKMAGENTA("darkMagenta"),
    DARKRED("darkRed"),
    DARKYELLOW("darkYellow"),
    DARKGRAY("darkGray"),
    LIGHTGRAY("lightGray"),
    BLACK("black"),


    ;
    public String val;


    WordHighlight(String val) {
        this.val = val;
    }
}
