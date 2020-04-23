package com.alanpoi.analysis.excel.exports;

import com.alanpoi.analysis.common.AlanColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.lang.reflect.Method;

/**
 * excel解析参数
 *
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
public class ExcelParseParam {

    private Integer index;
    
    private int height;

    private int color;

    private Object key;

    private String format;

    private String numFormat;

    private Method method;

    private CellStyle cellStyle;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        if (this.getColor() != AlanColor.NONE.index) {
            cellStyle.setFillForegroundColor((short) this.color);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        this.cellStyle = cellStyle;
    }

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
