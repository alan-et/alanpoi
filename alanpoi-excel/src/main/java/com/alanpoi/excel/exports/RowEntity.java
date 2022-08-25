package com.alanpoi.excel.exports;

import java.util.List;
import java.util.Objects;

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

        private String fieldName;

        private Object value;

        private String type;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (o instanceof String) {
                return Objects.equals(fieldName, (String) o);
            }
            ColEntity colEntity = (ColEntity) o;
            return Objects.equals(fieldName, colEntity.fieldName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fieldName);
        }
    }
}
