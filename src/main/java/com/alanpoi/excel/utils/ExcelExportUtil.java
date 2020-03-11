package com.alanpoi.excel.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

public class ExcelExportUtil {


    public static Workbook getWorkbook(List singleSheetData) {

        return null;

    }

    public static Workbook getWorkbook(List<JSONObject> singleSheetData, JSONObject headParam) {
        return null;
    }

    /**
     *
     * @param multipleSheetData sheet name and sheet Data
     * @param headParam
     * @return
     */
    public static Workbook getWorkbook(Map<String, List<JSONObject>> multipleSheetData, Map<String,JSONObject> headParam) {
        return null;
    }

}
