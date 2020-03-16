package com.alanpoi.excel.imports;

import lombok.Data;

import java.io.Serializable;

/**
 * excel导入响应对象
 */
@Data
public class ExcelImportRes implements Serializable {
    private int status;
    private String message;
    private String downErrorUrl;

}
