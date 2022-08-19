package com.alanpoi.excel.imports;

import com.alanpoi.common.annotation.DateFormat;
import com.alanpoi.common.annotation.NumFormat;
import com.alanpoi.common.util.AlanList;
import com.alanpoi.common.util.FieldUtil;
import com.alanpoi.excel.annotation.ExcelColumn;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.core.annotation.AnnotatedElementUtils;

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
            String supportPart = element.getAttributeValue("supportPart");
            if (StringUtils.isEmpty(supportPart)) supportPart = "true";
            String excelId = element.getAttributeValue("id");
            log.info("excelId=" + excelId);
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
                excel.setSupportPart(supportPart);
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
            if (sheet.getAttributeValue("head-start") != null) {
                sc.setHeadStart(Integer.parseInt(sheet.getAttributeValue("head-start")));
            }
            sc.setRowStart(Integer.parseInt(sheet.getAttributeValue("row-start")));
            sc.setColStart(Integer.parseInt(sheet.getAttributeValue("column-start")));
            try {
                sc.setT(Class.forName(sheet.getAttributeValue("vo")));
            } catch (ClassNotFoundException e) {
                log.error("", e);
            }
            List children = sheet.getChildren("column");
            String[] columns = new String[children.size()];
            String[] excelColumns = new String[children.size()];
            for (int i = 0; i < children.size(); i++) {
                columns[i] = ((Element) children.get(i)).getTextTrim();
                if (((Element) children.get(i)).getAttributeValue("name") != null) {
                    excelColumns[i] = ((Element) children.get(i)).getAttributeValue("name");
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
        if (!isExistExcelConfig(excelId)) {
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
//            ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
//            DateFormat dateFormat = field.getAnnotation(DateFormat.class);
//            NumFormat numFormat = field.getAnnotation(NumFormat.class);
            ExcelColumn excelColumn = AnnotatedElementUtils.findMergedAnnotation(field, ExcelColumn.class);
            DateFormat dateFormat = AnnotatedElementUtils.findMergedAnnotation(field, DateFormat.class);
            NumFormat numFormat = AnnotatedElementUtils.findMergedAnnotation(field, NumFormat.class);
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

    private boolean isExistExcelConfig(String excelId) {
        return map.keySet().contains(excelId);
    }

}
