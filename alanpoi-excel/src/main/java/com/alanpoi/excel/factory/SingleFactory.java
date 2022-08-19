package com.alanpoi.excel.factory;

import com.alanpoi.excel.exports.WorkbookManager;
import com.alanpoi.excel.exports.handle.ExportHandle;
import com.alanpoi.excel.imports.ExcelInitConfig;
import com.alanpoi.excel.imports.ExcelParser;
import com.alanpoi.excel.imports.handle.ExcelWorkbookManage;


public class SingleFactory {

    private static volatile ExcelWorkbookManage excelWorkbookManage = null;

    private static volatile WorkbookManager workbookManager = null;

    private static volatile ExcelInitConfig excelInitConfig = null;

    private static volatile ExportHandle exportHandle = null;


    private static volatile ExcelParser excelParser = null;

    public static ExcelWorkbookManage getExcelWorkbookManage() {
        if (excelWorkbookManage == null) {
            synchronized (ExcelWorkbookManage.class) {
                if (excelWorkbookManage == null) {
                    excelWorkbookManage = new ExcelWorkbookManage();
                }
            }
        }
        return excelWorkbookManage;
    }


    public static WorkbookManager getWorkbookManager() {
        if (workbookManager == null) {
            synchronized (WorkbookManager.class) {
                if (workbookManager == null) {
                    workbookManager = new WorkbookManager();
                }
            }
        }
        return workbookManager;
    }


    public static ExcelInitConfig getExcelInitConfig() {
        if (excelInitConfig == null) {
            synchronized (ExcelInitConfig.class) {
                if (excelInitConfig == null) {
                    excelInitConfig = new ExcelInitConfig();
                }
            }
        }
        return excelInitConfig;
    }


    public static ExportHandle getExportHandle() {
        if (exportHandle == null) {
            synchronized (ExportHandle.class) {
                if (exportHandle == null) {
                    exportHandle = new ExportHandle();
                }
            }
        }
        return exportHandle;
    }


    public static ExcelParser getExcelParser() {

        if (excelParser == null) {
            synchronized (ExcelParser.class) {
                if (excelParser == null) {
                    excelParser = new ExcelParser(getExcelInitConfig(),
                            getExcelWorkbookManage());
                }
            }
        }
        return excelParser;
    }
}
