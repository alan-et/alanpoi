package com.alanpoi.excel.exports;

import java.util.List;

public class RowEntity {

    private int rowIndex;

    private List<ColEntity> cols;


    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public List<ColEntity> getCols() {
        return cols;
    }

    public void setCols(List<ColEntity> cols) {
        this.cols = cols;
    }

    public static class ColEntity {
        private int position = -1;

        private Object value;

        private String type;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
