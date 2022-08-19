package com.alanpoi.excel.imports.handle;

import com.alanpoi.excel.imports.ExcelSheetData;

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
    /**
     * when error will 调用
     *
     * @param excelError
     */
    void error(ExcelError excelError);

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
    void end(List<ExcelSheetData> sheetDataList, Map<Serializable, Object> excelParam);
}
