package com.alanpoi.excel.parse;

import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.excel.exports.RowEntity;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class StandardConversion extends AbstractConversion {


    public StandardConversion(List<RowEntity> rowEntities,
                              Document document,
                              List<Object> sharedList,
                              File sheetFile) {
        this.rowEntities = rowEntities;
        this.document = document;
        this.sharedList = sharedList;
        this.sheetFile = sheetFile;
    }


    @Override
    public void start(BiFunction<Map<String, Object>, File, Boolean> callback) throws AlanPoiException {
        try {
            Element root = document.getRootElement();
            Element sheet = root.getChildren("sheetData", root.getNamespace()).get(0);
            RowEntity first = rowEntities.get(0);
            List<Element> rows = sheet.getChildren("row", sheet.getNamespace());
            for (Element row : rows) {
                String r = row.getAttributeValue("r");
                if (r.equals(String.valueOf(first.getRowIndex() + 1))) {
                    WorkbookBuild workbookBuild = new WorkbookBuild(rowEntities, row);
                    sheet.removeContent(row);
                    sheet.addContent(rows.size(), new Text("#alanpoi#replace#sheet"));
                    XMLOutputter outPutter = new XMLOutputter();
                    outPutter.output(document, new FileOutputStream(sheetFile));
                    Reader reader = new FileReader(sheetFile);
                    int len = 0;
                    StringWriter sw = new StringWriter();
                    while ((len = reader.read()) != -1) {
                        sw.write(len);
                    }
                    sw.flush();
                    sw.close();
                    String str = sw.toString().replace("#alanpoi#replace#sheet", "${sheetData}");
                    Writer writer = new FileWriter(sheetFile, false);
                    writer.write(str);
                    writer.flush();
                    writer.close();
                    Map<String, Object> param = new HashMap<>();
                    param.put("sheetData", workbookBuild.buildSheet());
                    callback.apply(param, sheetFile);
                    break;
                }
            }
        } catch (Exception e) {
            throw new AlanPoiException(e);
        }
    }
}
