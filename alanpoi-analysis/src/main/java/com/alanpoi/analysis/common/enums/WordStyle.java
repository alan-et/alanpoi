package com.alanpoi.analysis.common.enums;

public enum WordStyle {
    NONE(null,"无"),
    GAPLESS("a3","无间隔"),
    SUBTITLE("a4","副标题"),
    TITLE("a6","标题"),
    no_obvious_emphasis("a8","不明显强调"),
    EMPHASIS("a9","强调"),
    OBVIOUS_EMPHASIS("aa","明显强调"),
    MAIN_POINTS("ab","要点"),
    QUOTE("ac","引用"),
    OBVIOUS_QUOTE("ae","明显引用"),
    NO_OBVIOUS_REFERENCE("af0","不明显参考"),
    OBVIOUS_REFERENCE("af1","明显参考"),
    TITLE1("1","标题1"),
    TITLE2("2","标题2"),
    TITLE3("3","标题3"),
    TITLE4("4","标题4"),
    TITLE5("5","标题5"),
    TITLE6("6","标题6"),
    TITLE7("7","标题7"),

    ;
    public String val;

    private String desc;

    WordStyle(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
