package com.alanpoi.excel.imports;

import com.alanpoi.excel.common.PoiEventManager;
import com.alanpoi.excel.imports.handle.ExcelWorkbookManage;
import com.alanpoi.common.event.Event;
import com.alanpoi.common.event.EventListener;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Excel解析
 *
 * @param <T>
 * @author zhuoxun.peng
 * @since 2020-2-25
 */
public class ExcelParser<T> extends AbstractFileParser<T> implements EventListener {
    private final static Logger log = LoggerFactory.getLogger(ExcelParser.class);

    @Autowired
    public ExcelParser(ExcelInitConfig excelInitConfig,
                       ExcelWorkbookManage excelWorkbookManage) {
        this.excelInitConfig = excelInitConfig;
        this.excelWorkbookManage = excelWorkbookManage;
    }

    @PostConstruct
    public void init() {
        PoiEventManager.getDispatcher().on(PoiEventManager.POI_IMPORT_EVENT_NAME, this);
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
        List<ExcelColumnEntity> entities = sheet.getColumnEntities();
        if (!CollectionUtils.isEmpty(entities) && sheet.getColumn().length == 0) {
            for (int i = 0; i < entities.size(); i++) {
                ExcelColumnEntity entity = entities.get(i);
                fieldName = entity.getValue();
                String value = parseColumn(wrapper, row.getCell(entity.getIndex()), fieldName, entity);
                if (log.isDebugEnabled())
                    log.debug("excel data : " + i + " >> " + fieldName + " = " + value);
            }
        } else {
            for (short i = (short) sheet.getColStart(); i < sheet.getColumn().length + sheet.getColStart(); i++) {
                fieldName = sheet.getColumn()[i - sheet.getColStart()];
                if ("disabled".equals(fieldName)) {
                    continue;
                }
                ExcelColumnEntity entity = null;
                int columnInd = entities.indexOf(fieldName);
                if (columnInd != -1) {
                    entity = entities.get(columnInd);
                }
                String value = parseColumn(wrapper, row.getCell(entity.getIndex()), fieldName, entity);
                if (log.isDebugEnabled())
                    log.debug("excel data : " + i + " >> " + fieldName + " = " + value);
            }
        }
        return (T) wrapper.getWrappedInstance();
    }

    private String parseColumn(BeanWrapper wrapper, Cell cell, String fieldName, ExcelColumnEntity columnEntity) {
        String value = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date d = cell.getDateCellValue();
                        String fmt = columnEntity.getFormat() == null ? "yyyy-MM-dd HH:mm:ss" : columnEntity.getFormat();
                        DateFormat dateFormat = new SimpleDateFormat(fmt);
                        value = dateFormat.format(d);
                    } else {
                        String fmt = columnEntity.getNumFormat() == null ? "##############.###########" : columnEntity.getNumFormat();
                        DecimalFormat df = new DecimalFormat(fmt);
                        value = df.format(cell.getNumericCellValue());
                    }
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
                if (columnEntity.getField().getType() == Date.class || columnEntity.getField().getType() == java.sql.Date.class) {
                    try {
                        DateFormat dateFormat = new SimpleDateFormat(columnEntity.getFormat());
                        wrapper.setPropertyValue(fieldName, dateFormat.parse(value));
                    } catch (Exception e) {
                        log.warn("{}字段时间格式化异常:to {}", fieldName, columnEntity.getFormat());
                        wrapper.setPropertyValue(fieldName, value);
                    }
                } else {
                    wrapper.setPropertyValue(fieldName, value);
                }
            }
        }
        return value;
    }

    @Override
    public void onEvent(Event e) {
        if (e == null) return;
        try {
            if (PoiEventManager.POI_IMPORT_EVENT_NAME.equals(e.getName())) {
                if (e.getData() == null) return;
                Map<String, Object> param = (Map<String, Object>) e.getData();
                Map<Serializable, Object> excelParam = null;
                String excelId = (String) param.get("excelId");
                InputStream inputStream = (InputStream) param.get("inputStream");
                String fileName = (String) param.get("fileName");
                if (param.get("excelParam") != null) excelParam = (Map<Serializable, Object>) param.get("excelParam");
                importData(excelId, inputStream, fileName, excelParam);
            }
        } catch (Exception error) {
            log.error("POI Event error:", error);
        }
    }
}
