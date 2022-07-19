package com.alanpoi.analysis.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportMultipleSheetParam {
    private volatile Map<String, MetaInfo> map = new HashMap<>();

    /**
     * put data to memory
     *
     * @param sheetIndex
     * @param sheetName
     * @param cls
     * @param data
     * @param specifyCol
     */
    public void put(int sheetIndex, String sheetName, Class<?> cls, Collection<?> data, Map<Integer, List<String>> specifyCol) {
        MetaInfo metaInfo = new ExportMultipleSheetParam.MetaInfo();
        metaInfo.setCls(cls);
        metaInfo.setDataCol(data);
        metaInfo.setIndex(sheetIndex);
        metaInfo.setSpecifyCol(specifyCol);
        map.put(sheetName, metaInfo);
    }

    public void put(int sheetIndex, String sheetName, Class<?> cls, Collection<?> data) {
        put(sheetIndex, sheetName, cls, data, new HashMap<>());
    }

    public void put(String sheetName, Class<?> cls, Collection<?> data) {
        put(-1, sheetName, cls, data);
    }


    public Collection<?> getData(String sheetName) {
        return map.get(sheetName).getDataCol();

    }

    public int getSheetIndex(String sheetName) {
        return map.get(sheetName).getIndex();

    }

    public Class<?> getDataClass(String sheetName) {
        return map.get(sheetName).getCls();

    }

    public Map<Integer, List<String>> getSpecifyCol(String sheetName) {
        return map.get(sheetName).getSpecifyCol();

    }

    public Map<String, MetaInfo> getMap() {
        return map;
    }

    public static class MetaInfo {
        private int index;

        private String sheetName;

        private Map<Integer, List<String>> specifyCol;

        private Class<?> cls;
        private Collection<?> dataCol;

        public Class<?> getCls() {
            return cls;
        }

        public void setCls(Class<?> cls) {
            this.cls = cls;
        }

        public Collection<?> getDataCol() {
            return dataCol;
        }

        public void setDataCol(Collection<?> dataCol) {
            this.dataCol = dataCol;
        }

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

        public Map<Integer, List<String>> getSpecifyCol() {
            if (specifyCol == null) {
                return new HashMap<>();
            }
            return specifyCol;
        }

        public void setSpecifyCol(Map<Integer, List<String>> specifyCol) {
            this.specifyCol = specifyCol;
        }
    }
}
