package com.alanpoi.analysis.excel.imports.handle;

import com.alanpoi.analysis.excel.imports.ExcelImportRes;
import com.alanpoi.analysis.excel.imports.ExcelSheetData;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Excel消费接口
 *
 * @author zhuoxun.peng
 * @since 2020-2-26
 */
public interface ExcelConsumeInterface {

    void begin(String workbookId);

    /**
     * when error will call
     *
     * @param excelImportRes
     */
    void error(String workbookId, ExcelImportRes excelImportRes);

    /**
     * custom valid data
     *
     * @param workbookId
     * @param sheetDataList
     */
    void validData(String workbookId, List<ExcelSheetData> sheetDataList, Map<Serializable, Object> excelParam);

    /**
     * @param sheetDataList return success data
     */
    void end(String workbookId, List<ExcelSheetData> sheetDataList, Map<Serializable, Object> excelParam);
}
