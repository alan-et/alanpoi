package com.alanpoi.analysis.excel.exports;

import com.alanpoi.analysis.common.enums.AlanColor;
import com.alanpoi.analysis.common.enums.DataType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.reflect.Method;
import java.util.Set;

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

    private String sourceLink;

    private Method linkMethod;

    private Set<String> specifyCol;

    private DataType dataType;

    /**
     * 自动合并
     *
     * @since 1.3.7
     */
    private boolean autoMerge;


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

    public Method getLinkMethod() {
        return linkMethod;
    }

    public void setLinkMethod(Method linkMethod) {
        this.linkMethod = linkMethod;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public Set<String> getSpecifyCol() {
        return specifyCol;
    }

    public void setSpecifyCol(Set<String> specifyCol) {
        this.specifyCol = specifyCol;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean isAutoMerge() {
        return autoMerge;
    }

    public void setAutoMerge(boolean autoMerge) {
        this.autoMerge = autoMerge;
    }
}
