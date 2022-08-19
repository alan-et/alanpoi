package com.alanpoi.excel.exports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 1.3.7
 */
public class CellDict {
    protected static final Logger logger = LoggerFactory.getLogger(CellDict.class);
    Dict[] dictArr;
    int index;

    int rowSize;

    public CellDict(int length, int rowSize) {
        this.dictArr = new Dict[length];
        this.rowSize = rowSize;
    }


    public synchronized void putCell(Object value) {
        dictArr[index++] = new Dict(value);
    }

    public synchronized void putCell(Object value, int index) {
        dictArr[index] = new Dict(value);
    }

    public Object getCell(int index) {
        return dictArr[index] != null ? dictArr[index].value : null;
    }

    public int increase(int index, int num) {
        try {
            int duplicateNum = dictArr[index].duplicateNum;
            return dictArr[index].duplicateNum = duplicateNum + num;
        } catch (Exception e) {
            logger.warn("合并单元格发送异常，详情请查看日志！", e);
        }
        return 0;
    }

    public int getCellDuplicateNum(int index) {
        return dictArr[index].duplicateNum;
    }

    public static class Dict {
        private Object value;
        private int duplicateNum;

        public Dict(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public int getDuplicateNum() {
            return duplicateNum;
        }

        public void setDuplicateNum(int duplicateNum) {
            this.duplicateNum = duplicateNum;
        }
    }

    public int getRowSize() {
        return rowSize;
    }
}
