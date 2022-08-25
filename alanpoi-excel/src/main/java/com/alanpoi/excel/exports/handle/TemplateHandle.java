package com.alanpoi.excel.exports.handle;


import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.excel.parse.ExcelDataParse;
import com.alanpoi.excel.parse.ExcelZipPackage;
import com.alanpoi.excel.parse.ZipPackage;


import java.util.List;


public class TemplateHandle {

    public ZipPackage getExcelStream(String templatePath, List<?> dataList, Class<?> cls, int dataRowIndex) throws AlanPoiException {
        try {
            ZipPackage zipPackage = new ExcelZipPackage(templatePath);
            ExcelDataParse excelDataParse = new ExcelDataParse(zipPackage);
            excelDataParse.exec(dataList, cls, dataRowIndex);
            return zipPackage;
        } catch (Exception e) {
            throw new AlanPoiException(e);
        }

    }
}
