package com.alanpoi.analysis.excel.imports;

import lombok.Data;
import org.apache.poi.util.Removal;

import java.io.Serializable;
import java.util.Map;

/**
 * excel导入响应对象
 */
@Data
public class ExcelImportRes implements Serializable {
    private int status;
    @Deprecated
    @Removal(
            version = "1.3.1"
    )
    private String message;
    /**
     * @since 1.3.1
     */
    private Map<String, SheetInfo> errorMap;

    private String downErrorUrl;

    /**
     * If the import fails, the error file is recorded
     *
     * @since 1.3.2
     */
    private ErrorFile errorFile;

    public static class SheetInfo {
        private int index;
        private String sheetName;
        private int sucNum;
        private int failNum;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

        public int getSucNum() {
            return sucNum;
        }

        public void setSucNum(int sucNum) {
            this.sucNum = sucNum;
        }

        public int getFailNum() {
            return failNum;
        }

        public void setFailNum(int failNum) {
            this.failNum = failNum;
        }
    }
}
