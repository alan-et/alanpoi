package com.alanpoi.common.util;

public enum Placeholder {

    TYPE0("\\$\\{(.*?)\\}", "\\$\\{", "替换${}"),
    TYPE1("\\#\\{(.*?)\\}", "\\#\\{", "替换#{}");

    public String regex;
    public String begin;
    public String desc;

    Placeholder(String regex, String begin, String desc) {
        this.regex = regex;
        this.begin = begin;
        this.desc = desc;
    }
}
