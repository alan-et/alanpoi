package com.alanpoi.excel.imports.handle;


/**
 * @author zhuoxun.peng
 * @since 2020-2-26
 */
public class RowError {
    private int rowIndex;
    private String errorMsg;

    public RowError(int rowIndex, String errorMsg) {
        this.rowIndex = rowIndex;
        this.errorMsg = errorMsg;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
