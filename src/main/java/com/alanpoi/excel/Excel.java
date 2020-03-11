package com.alanpoi.excel;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
    private List<ExcelSheet> excelSheets;

    public Excel() {

    }
}
