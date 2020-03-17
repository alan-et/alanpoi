package com.alanpoi.excel.imports;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * excel全局参数
 *
 * @author zhuoxun.peng
 */
@Data
public class Excel implements Serializable {

    private long frameId;
    private Class consume;
    private String fileName;
    private Map<Serializable,Object> customParam;
    private List<ExcelSheet> excelSheets;

    public Excel() {

    }
}
