package com.alanpoi.excel.exports;

import java.lang.reflect.Method;

/**
 * excel解析参数
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
public class ExcelParseParam {

    private Object key;

    private String format;

    private String numFormat;

    private Method method;

    public Method getMethod() {
        return method;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNumFormat() {
        return numFormat;
    }

    public void setNumFormat(String numFormat) {
        this.numFormat = numFormat;
    }
}
