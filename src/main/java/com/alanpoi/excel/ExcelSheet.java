package com.alanpoi.excel;

import lombok.Data;

import java.io.Serializable;

/**
 * excel全局参数
 */
@Data
public class ExcelSheet implements Serializable {
    private int index = 0;
    private int rowStart = 1;  //开始行
    private int colStart = 1;  //开始列

    private String[] column = null; //映射列(属性)名称集合

    private Class T;

    public ExcelSheet(){

    }
}
