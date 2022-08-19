package com.alanpoi.excel.exports;

import com.alanpoi.common.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 工作表实体
 *
 * @author zhuoxun.peng
 * @since 2020-8-15
 */
@Slf4j
public class WorkbookEntity {

    private String workbookId;
    private Workbook workbook;

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public Workbook getWorkbook() {
        if (workbook == null) {
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }

    public String getWorkbookId() {
        return workbookId;
    }

    public void setWorkbookId(String workbookId) {
        this.workbookId = workbookId;
    }

    public void close() {
        try {
            WorkbookManager workbookManager = ApplicationUtil.getBean(WorkbookManager.class);
            if (workbookManager != null)
                workbookManager.close(workbookId);
            else {
                if (workbook != null)
                    workbook.close();
            }
        } catch (Exception e) {
            log.error("Close workbook exception:" + e);
        }
    }
}
