package com.alanpoi.excel.imports;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * excel全局参数
 *
 * @author zhuoxun.peng
 */
public class Excel implements Serializable {

    private Class consume;
    private String fileName;
    private Map<Serializable, Object> customParam;
    private List<ExcelSheet> excelSheets;

    public Excel() {

    }

    public Class getConsume() {
        return consume;
    }

    public void setConsume(Class consume) {
        this.consume = consume;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<Serializable, Object> getCustomParam() {
        return customParam;
    }

    public void setCustomParam(Map<Serializable, Object> customParam) {
        this.customParam = customParam;
    }

    public List<ExcelSheet> getExcelSheets() {
        return excelSheets;
    }

    public void setExcelSheets(List<ExcelSheet> excelSheets) {
        this.excelSheets = excelSheets;
    }
}
