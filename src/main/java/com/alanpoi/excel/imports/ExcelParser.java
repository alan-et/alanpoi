package com.alanpoi.excel.imports;

import com.alanpoi.common.ExecutorTools;
import com.alanpoi.common.AbstractExcelService;
import com.alanpoi.excel.imports.handle.ExcelWorkbookManage;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Excel解析
 *
 * @param <T>
 * @author zhuoxun.peng
 * @since 2020-2-25
 */
@Slf4j
public class ExcelParser<T> extends AbstractFileParser<T> {


    @Autowired
    public ExcelParser(ExcelInitConfig excelInitConfig,
                       ExcelWorkbookManage excelWorkbookManage,
                       StringRedisTemplate redisTemplate,
                       ExecutorTools executorTools) {
        this.excelInitConfig = excelInitConfig;
        this.excelWorkbookManage = excelWorkbookManage;
        this.executorTools=executorTools;
        this.redisTemplate=redisTemplate;
    }

    @Override
    protected T getData(ExcelSheet sheet, Row row) throws Exception {
        String fieldName = null;
        //组装对象
//        T t = (T) sheet.getT().newInstance();
        BeanWrapper wrapper = new BeanWrapperImpl(sheet.getT().newInstance());
        try {
            wrapper.getPropertyValue("rowIndex");
            wrapper.setPropertyValue("rowIndex", row.getRowNum());
        } catch (Exception e) {
            log.warn("请在导入实体类加入rowIndex;不然消费类end方法中接收到的数据不会过滤异常数据");
        }
        for (short i = (short) sheet.getColStart(); i < sheet.getColumn().length + sheet.getColStart(); i++) {
            Cell cell = row.getCell(i);
            fieldName = sheet.getColumn()[i - sheet.getColStart()];
            if ("disabled".equals(fieldName)) {
                continue;
            }
            String value = null;
            if (cell != null) {
                switch (cell.getCellType()) {
                    case NUMERIC:
                        value = String.valueOf(cell.getNumericCellValue());
                        break;
                    case STRING:
                        value = cell.getStringCellValue();
                        if (null != value) {
                            value = value.toString().trim();
                        }
                        break;
                    default:
                }
                if (null != value) {
                    //给对象指定属性名附值
                    wrapper.setPropertyValue(fieldName, value);
                }
            }
            if (log.isDebugEnabled())
                log.debug("excel data : " + i + " >> " + fieldName + " = " + value);
        }
        return (T) wrapper.getWrappedInstance();
    }
}
