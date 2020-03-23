package com.alanpoi.excel.imports;


import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Excel column entity
 *
 * @author pengzhuoxun
 * @since 2020-3-23
 */
public class ExcelColumnEntity {
    private int index;

    private String name;

    private String value;

    private String format;

    private String numFormat;

    private Field field;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o instanceof String) {
            return Objects.equals(value, (String) o) || Objects.equals(name, (String) o);
        }
        ExcelColumnEntity that = (ExcelColumnEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
