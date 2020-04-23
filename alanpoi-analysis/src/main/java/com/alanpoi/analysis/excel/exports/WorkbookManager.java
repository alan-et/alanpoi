package com.alanpoi.analysis.excel.exports;

import com.alanpoi.analysis.common.ExcelType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
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

    public WorkbookManager getWorkbookManager(ExcelType excelType) {
        WorkbookManager workbookManager = new WorkbookManager();

        workbookManager.workbook = newWorkbook(excelType);
        workbookManager.workbookId = UUID.randomUUID().toString();
        workbookManagerMap.put(workbookManager.workbookId, workbookManager);
        return workbookManagerMap.get(workbookManager.workbookId);
    }

    public Workbook getWorkbook() {
        if (workbook == null) {
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }

    public static Workbook newWorkbook(ExcelType excelType) {
        return newWorkbook(excelType, null);
    }

    public static Workbook newWorkbook(ExcelType excelType, Collection<?> collection) {
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
        if (collection == null) {
            workbook.createSheet();
            return workbook;
        }
        for (Iterator it = collection.iterator(); it.hasNext(); workbook.createSheet()) {
            it.next();
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
        } finally {
            workbookManagerMap.remove(workbookId);
        }
    }

}
