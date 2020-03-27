package com.alanpoi.analysis.excel.imports;

import com.alanpoi.common.util.AlanList;
import com.alanpoi.common.util.FieldUtil;
import com.alanpoi.analysis.excel.annotation.DateFormat;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.NumFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * excel帮助类
 *
 * @author zhuoxun.peng
 * @since 2020-2-25
 */
@Slf4j
public class ExcelInitConfig implements Serializable {
    private String fileName = "excel-config.xml";  //Excel结构配置文件

    private HashMap map = new HashMap();


    public ExcelInitConfig() {

    }

    @PostConstruct
    public void initConfig() {
        SAXBuilder sb = new SAXBuilder();
        InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
        log.debug("in = " + in);
        Document doc = null;
        try {
            doc = sb.build(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
        parseRoot(root);
    }

    protected void parseRoot(Element root) {
        List list = root.getChildren();
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            String excelId = element.getAttributeValue("id");
            log.debug("excelId=" + excelId);
            if (excelId == null) {
                continue;
            } else {
                Excel excel = new Excel();
                List<ExcelSheet> excelSheets = buildSheetContent(excelId, element);
                excel.setExcelSheets(excelSheets);
                String className = element.getAttributeValue("consume");
                try {
                    if (StringUtils.isNotEmpty(className)) {
                        excel.setConsume(Class.forName(className));
                    }
                } catch (ClassNotFoundException e) {
                    log.error("", e);
                }
                map.put(excelId, excel);
            }
        }
    }

    protected List<ExcelSheet> buildSheetContent(String key, Element element) {
        AtomicBoolean isHaveExcelCol = new AtomicBoolean(false);
        List<ExcelSheet> scList = new ArrayList<>();
        List<Element> sheets = element.getChildren("sheet");
        sheets.forEach(sheet -> {
            ExcelSheet sc = new ExcelSheet();
            sc.setIndex(Integer.parseInt(sheet.getAttributeValue("index")));
            log.debug(">> : index=" + sc.getIndex());
            if (sheet.getAttributeValue("head-start") != null) {
                sc.setHeadStart(Integer.parseInt(sheet.getAttributeValue("head-start")));
                log.debug(">> : head-start=" + sc.getHeadStart());
            }
            sc.setRowStart(Integer.parseInt(sheet.getAttributeValue("row-start")));
            log.debug(">> : row-start=" + sc.getColStart());
            sc.setColStart(Integer.parseInt(sheet.getAttributeValue("column-start")));
            log.debug(">> : column-start=" + sc.getRowStart());
            try {
                sc.setT(Class.forName(sheet.getAttributeValue("vo")));
            } catch (ClassNotFoundException e) {
                log.error("", e);
            }
            List children = sheet.getChildren("column");
            String[] columns = new String[children.size()];
            String[] excelColumns = new String[children.size()];
            log.debug("column count=" + children.size());
            for (int i = 0; i < children.size(); i++) {
                columns[i] = ((Element) children.get(i)).getTextTrim();
                log.debug(">> : column=" + columns[i]);
                if (((Element) children.get(i)).getAttributeValue("name") != null) {
                    excelColumns[i] = ((Element) children.get(i)).getAttributeValue("name");
                    log.debug(">> : excelColumn=" + excelColumns[i]);
                    isHaveExcelCol.set(true);
                }
            }
            sc.setColumn(columns);
            sc.setExcelColumn(isHaveExcelCol.get() ? excelColumns : new String[0]);
            scList.add(sc);
        });

        return scList;
    }


    public Excel getExcelConfig(String excelId) {
        if (!isExistExcelConifg(excelId)) {
            throw new RuntimeException("excel config not find");
        }
        Excel excel = (Excel) map.get(excelId);
        List<ExcelSheet> sheet = excel.getExcelSheets();
        sheet.forEach(e -> {
            e.setColumnEntities(parseAnnotation(e.getT()));
        });
        return excel;
    }

    private List<ExcelColumnEntity> parseAnnotation(Class<?> c) {
        Field[] fields = FieldUtil.getClassFields(c);

        List<ExcelColumnEntity> entities = new AlanList<>();
        for (int i = 0; i < fields.length; i++) {
            ExcelColumnEntity entity = new ExcelColumnEntity();
            Field field = fields[i];
            ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
            DateFormat dateFormat = field.getAnnotation(DateFormat.class);
            NumFormat numFormat = field.getAnnotation(NumFormat.class);
            entity.setValue(field.getName());
            entity.setField(field);
            if (excelColumn != null) {
                if (!excelColumn.isExist()) {
                    continue;
                }
                entity.setIndex(StringUtils.isEmpty(excelColumn.index()) ? i : Integer.valueOf(excelColumn.index()));
                entity.setName(excelColumn.name());
            }
            if (dateFormat != null) {
                entity.setFormat(dateFormat.value());
            }
            if (numFormat != null) {
                entity.setNumFormat(numFormat.value());
            }
            entities.add(entity);
        }
        return entities;
    }

    private boolean isExistExcelConifg(String excelId) {
        return map.keySet().contains(excelId);
    }

}
