package com.alanpoi.excel.utils;

import com.alanpoi.excel.AbstractFileParser;
import com.alanpoi.excel.ApplicationUtil;
import com.alanpoi.excel.ExcelSheetData;

import java.io.InputStream;
import java.util.List;

public class ExcelImportUtil {

    public static List<ExcelSheetData> getExcelData(String excelId, InputStream inputStream, String fileName) {
        AbstractFileParser parser = ApplicationUtil.getBean(AbstractFileParser.class);
        return parser.getData(excelId, inputStream, fileName);
    }

    public static <T> List<T> getExcelData(InputStream inputStream, Class<? extends T> c) {
        return null;
    }
}
