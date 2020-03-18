package com.alanpoi.excel.exports;

import com.alanpoi.common.ExcelType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 导出工作表管理
 *
 * @author zhuoxun.peng
 * @since 2020-3-15
 */
@Slf4j
public class WorkbookManager {

    private Map<String, WorkbookManager> workbookManagerMap = new ConcurrentHashMap();
    private String workbookId;
    private Workbook workbook;

    @PreDestroy
    public void destroy() {
        for (String workbookId : workbookManagerMap.keySet()) {
            Workbook workbook = workbookManagerMap.get(workbookId).getWorkbook();
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.info("stop {},workbook close exception:{}", workbookId, e);
                }
            }
        }
        workbookManagerMap.clear();
    }

    public WorkbookManager getWorkbookManager(String url) {
        if (workbookManagerMap.containsKey(url)) {
            return workbookManagerMap.get(url);
        }
        WorkbookManager workbookManager = new WorkbookManager();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(new File(url)));
        } catch (Exception e) {
            log.error("", e);
        }
        workbookManager.setWorkbookId(url);
        workbookManager.setWorkbook(workbook);
        workbookManagerMap.put(url, workbookManager);
        return workbookManagerMap.get(workbookManager.workbookId);
    }

    public WorkbookManager getWorkbookManager(ExcelType excelType, String workbookId) {
        WorkbookManager workbookManager = new WorkbookManager();

        workbookManager.workbook = getWorkbook(excelType);
        workbookManager.workbookId = workbookId;
        workbookManagerMap.put(workbookId, workbookManager);
        return workbookManagerMap.get(workbookId);
    }

    public Workbook getWorkbook() {
        if (workbook == null) {
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }

    public static Workbook newWorkbook(ExcelType excelType) {
        Workbook workbook = null;
        try {
            if (excelType == ExcelType.EXCEL_2003) {
                workbook = new HSSFWorkbook();
            } else {
                workbook = new XSSFWorkbook();
            }

        } catch (Exception e) {
            log.error("", e);
        }
        return workbook;
    }

    public Workbook getWorkbook(ExcelType excelType) {
        try {
            if (workbook == null) {
                if (excelType == ExcelType.EXCEL_2003) {
                    workbook = new HSSFWorkbook();
                } else {
                    workbook = new XSSFWorkbook();
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public String getWorkbookId() {
        return workbookId;
    }

    public void setWorkbookId(String workbookId) {
        this.workbookId = workbookId;
    }

    public void close(String workbookId) {
        try {
            Workbook wb = workbookManagerMap.get(workbookId).getWorkbook();
            wb.close();
        } catch (Exception e) {
            log.error("Close workbook exception:" + e);
        }
    }

}
