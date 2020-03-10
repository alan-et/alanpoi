package com.qizhidao.alanpoi.excel.handle;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工作表管理
 *
 * @author zhuoxun.peng
 * @since 2020-2-26
 */
@Slf4j
public class ExcelWorkbookManage {
    private Map<String, Workbook> workbookMap = new ConcurrentHashMap();
    private Map<String, ExcelError> errorMsgMap = new ConcurrentHashMap();

    @PreDestroy
    public void stop() {
        for (String workbookId : workbookMap.keySet()) {
            Workbook workbook = workbookMap.get(workbookId);
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.info("stop {},workbook close exception:{}", workbookId, e);
                }
            }
        }
        workbookMap.clear();
    }

    protected Workbook getWorkbook(String workbookId) {
        return workbookMap.get(workbookId);
    }

    protected ExcelError getExcelError(String workbookId) {
        return errorMsgMap.get(workbookId);
    }

    public void addWorkbook(String id, Workbook workbook) {
        workbookMap.put(id, workbook);
    }

    public void removeWorkbook(String id) {
        Workbook workbook = workbookMap.get(id);
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                log.info("removeWorkbook {},workbook close exception:{}", id, e);
            }
        }
        workbookMap.remove(id);
        errorMsgMap.remove(id);
    }


    public void addErrorInfo(String workbookId, int sheetIndex, RowError rowError) {
        addErrorInfo(workbookId, sheetIndex, Arrays.asList(rowError));
    }

    public void addErrorInfo(String workbookId, int sheetIndex, List<RowError> rowErrors) {
        if (CollectionUtils.isEmpty(rowErrors)) {
            return;
        }
        ExcelError excelError = errorMsgMap.get(workbookId);
        if (excelError == null) {
            excelError = new ExcelError();
        }
        List<ExcelError.SheetError> sheetErrorList = excelError.getSheetErrors();
        if (CollectionUtils.isEmpty(sheetErrorList)) {
            sheetErrorList = new ArrayList<>();
            ExcelError.SheetError sheetError = new ExcelError.SheetError(sheetIndex, rowErrors);
            sheetErrorList.add(sheetError);
        } else {
            boolean bool = false;
            for (ExcelError.SheetError sheetError : sheetErrorList) {
                if (sheetError.getIndex() == sheetIndex) {
                    sheetError.getRowErrors().addAll(rowErrors);
                    bool = true;
                }
            }
            if (!bool) {
                ExcelError.SheetError sheetError = new ExcelError.SheetError(sheetIndex, rowErrors);
                sheetErrorList.add(sheetError);
            }
        }
        excelError.setSheetErrors(sheetErrorList);
        errorMsgMap.put(workbookId, excelError);
    }

}
