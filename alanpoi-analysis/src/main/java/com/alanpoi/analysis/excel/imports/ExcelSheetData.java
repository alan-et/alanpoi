package com.alanpoi.analysis.excel.imports;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * excel全局sheet数据
 */
@Data
public class ExcelSheetData<T> implements Serializable {
    private int index = 0;
    private int rowStart;
    private String sheetName;
    private List<T> data;

    public ExcelSheetData(){

    }
}
