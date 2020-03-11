package com.alanpoi.excel.handle;

import lombok.Data;

import java.util.List;

/**
 * Excel错误数据对象
 * @author zhuouxn.peng
 * @since 2020-2-26
 */
@Data
public class ExcelError {

    private List<SheetError> sheetErrors;

    public static class SheetError {
        private int index;
        private List<RowError> rowErrors;

        public SheetError(int index, List<RowError> rowErrors) {
            this.index = index;
            this.rowErrors = rowErrors;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public List<RowError> getRowErrors() {
            return rowErrors;
        }

        public void setRowErrors(List<RowError> rowErrors) {
            this.rowErrors = rowErrors;
        }
    }
}
