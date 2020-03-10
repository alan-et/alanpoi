package com.qizhidao.alanpoi.excel.handle;

import lombok.Data;

/**
 * @author zhuoxun.peng
 * @since 2020-2-26
 */
@Data
public class RowError {
    private int rowIndex;
    private String errorMsg;

    public RowError(int rowIndex, String errorMsg) {
        this.rowIndex = rowIndex;
        this.errorMsg = errorMsg;
    }
}
