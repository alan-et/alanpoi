package com.alanpoi.excel.exports;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;


/**
 * 导出工作表管理
 *
 * @author zhuoxun.peng
 * @since 2020-3-15
 */
@Slf4j
public class WorkbookManager {
    private static Workbook workbook;

    public Workbook getWorkbook(String url) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(new File(url)));
        } catch (Exception e) {
            log.error("", e);
        }
        this.setWorkbook(workbook);
        return workbook;
    }

    public static Workbook getWorkbook() {
        return workbook;
    }

    public static void setWorkbook(Workbook workbook) {
        WorkbookManager.workbook = workbook;
    }
}
