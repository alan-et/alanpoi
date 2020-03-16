package com.alanpoi.excel.imports;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        SAXBuilder sb = new SAXBuilder(); // 新建立构造器
        InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
        log.debug("in = " + in);
        Document doc = null;
        try {
            doc = sb.build(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
        parseRoot(root); //解析
    }

    /**
     * 解析xml，初始化map
     */
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

    /**
     * 构建预定义结构
     * 包含内容：sheet页--》index   开始行--> row-start  开始列--> column-start
     */
    protected List<ExcelSheet> buildSheetContent(String key, Element element) {
        List<ExcelSheet> scList = new ArrayList<>();
        List<Element> sheets = element.getChildren("sheet");
        sheets.forEach(sheet -> {
            ExcelSheet sc = new ExcelSheet();
            sc.setIndex(Integer.parseInt(sheet.getAttributeValue("index")));
            log.debug(">> : index=" + sc.getIndex());
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
            log.debug("column count=" + children.size());
            for (int i = 0; i < children.size(); i++) {
                columns[i] = ((Element) children.get(i)).getTextTrim();
                log.debug(">> : column=" + columns[i]);
            }
            sc.setColumn(columns);
            scList.add(sc);
        });

        return scList;
    }


    /**
     * 取得【配置文件中某一结构的所有[属性名称/字段名称]】
     */
    public Excel getExcelSheet(String excelId) {
        return (Excel) map.get(excelId);
    }

}
