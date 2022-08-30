package com.alanpoi.excel.parse;

import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.common.util.*;
import com.alanpoi.excel.common.Hyperlink;
import com.alanpoi.excel.exports.RowEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.ObjectUtils;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ExcelZipPackage extends ZipPackage {

    private static Logger log = LoggerFactory.getLogger(ExcelZipPackage.class);

    public static String ZIP_ENTRY_XL = "xl/worksheets/sheet";

    public static String ZIP_ENTRY_XL_REL = "xl/worksheets/_rels/sheet";

    public static String ZIP_ENTRY_DRAWING = "xl/drawings/drawing/drawing";

    public static String ZIP_ENTRY_DRAWING_REL = "xl/drawings/drawing/_rels/drawing";

    public static String ZIP_ENTRY_SHARED_STRINGS = "xl/sharedStrings.xml";

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SAXBuilder saxBuilder;

    public ExcelZipPackage(String path) throws IOException {
        super(path, "excel");
        saxBuilder = new SAXBuilder();
        parse();
    }


    public void parse() throws IOException {
        ZipFile originZf = null;
        InputStream originIs = null;
        ZipInputStream zipInputStream = null;
        try {
            // 原始压缩文件
            originZf = new ZipFile(inFile);
            //获取原始压缩文件流
            zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(inFile)));
            ZipEntry originZi = null;
            while ((originZi = zipInputStream.getNextEntry()) != null) {
                String entryName = originZi.getName();
                if (entryName.startsWith(ZIP_ENTRY_XL)) {
                    //获取原始压缩文件 文档文件
                    originIs = originZf.getInputStream(originZi);
                    File file = FileUtils.saveTempFile(originIs, "sheet", ".ftl", FileUtils.getTmpDir() + "template");
                    zipItem.put(ZIP_ENTRY_XL, file);
                } else if (entryName.startsWith(ZIP_ENTRY_XL_REL)) {
                    //获取原始压缩文件 文档关系文件
                    originIs = originZf.getInputStream(originZi);
                    File file = FileUtils.saveTempFile(originIs, "sheet-rel", ".ftl", FileUtils.getTmpDir() + "template");
                    //获取文档关系模版
                    zipItem.put(ZIP_ENTRY_XL_REL, file);
                } else if (entryName.startsWith(ZIP_ENTRY_SHARED_STRINGS)) {
                    //获取原始压缩文件 文档关系文件
                    originIs = originZf.getInputStream(originZi);
                    File file = FileUtils.saveTempFile(originIs, "shared", ".ftl", FileUtils.getTmpDir() + "template");
                    zipItem.put(ZIP_ENTRY_SHARED_STRINGS, file);
                } else {

                }
                if (originIs != null) {
                    originIs.close();
                }
                // 关闭此 entry
                zipInputStream.closeEntry();
            }
        } catch (IOException ioException) {
            throw ioException;
        } finally {
            CloseUtils.close(originZf);
            CloseUtils.close(zipInputStream);
        }
    }


    public void writeShared(List<?> dataList, ReflectorManager reflectorManager, int beginRow) throws IOException, TemplateException, JDOMException {
        File sharedStrings = this.getEntity(ExcelZipPackage.ZIP_ENTRY_SHARED_STRINGS);
//        InputStream in = new FileInputStream(sharedStrings);
        Document doc = saxBuilder.build(sharedStrings);
        List<Field> fields = reflectorManager.getFieldList();
        Element root = doc.getRootElement();
        List<Element> childList = root.getChildren("si", root.getNamespace());
        String text = root.getValue();
        List<String> strList = StringUtils.findReplaceAll(text, Placeholder.TYPE0);
        strList = strList.stream().map(e -> {
            if (e.contains(":")) {
                String val = e.split(":")[1];
                if (val.contains("/.")) {
                    e = val.split("\\.")[1];
                }
            }
            if (e.contains("?")) {
                e = e.split("\\?")[0];
            }
            return e;
        }).collect(Collectors.toList());

        List<Object> valList = new ArrayList<>();
        List<Object> replaceList = new ArrayList<>();
        int initNum = childList.size();
        childList.forEach(e -> {
            valList.add(e.getValue());
        });
        List<RowEntity> rowEntities = new ArrayList<>();

        int p = 0;
        for (int i = 0; i < dataList.size(); i++) {
            RowEntity rowEntity = new RowEntity();
            rowEntity.setRowIndex(i + beginRow);
            List<RowEntity.ColEntity> cols = new AlanList<>();
            for (int j = 0; j < fields.size(); j++) {
                Field field = fields.get(j);
                if (strList.contains(field.getName())) {
                    RowEntity.ColEntity colEntity = new RowEntity.ColEntity();
                    try {
                        Method method = reflectorManager.getGetMethod(field.getName());
                        Object value = method.invoke(dataList.get(i));
                        if (value instanceof Number) {
                            colEntity.setType("n");
                        } else {
                            colEntity.setType("s");
                            if (ObjectUtils.isNotEmpty(value)) {
                                if (value instanceof Date) {
                                    value = format.format(value);
                                }
//                                int index = valList.indexOf(colEntity.getValue());
//                                if (index != -1) {
//                                    colEntity.setPosition(initNum + index);
//                                } else {
                                replaceList.add(value);
                                colEntity.setPosition(initNum + p++);
//                                }
                            }
                        }
                        colEntity.setFieldName(field.getName());
                        colEntity.setValue(value);
                    } catch (Exception e) {
                        log.warn("", e);
                    }
                    cols.add(colEntity);
                }
            }
            rowEntity.setCols(cols);
            rowEntities.add(rowEntity);
        }
        root.addContent(initNum, new Text("#alanpoi#replace"));
        XMLOutputter outPutter = new XMLOutputter();
        outPutter.output(doc, new FileOutputStream(sharedStrings));
        Reader reader = new FileReader(sharedStrings);
        int len = 0;
        StringWriter sw = new StringWriter();
        while ((len = reader.read()) != -1) {
            sw.write(len);
        }
        sw.flush();
        sw.close();
        String result = sw.toString();
        List<String> strList1 = StringUtils.findReplaceAllWithP(result, Placeholder.TYPE0);
        for (String s : strList1) {
            result = result.replace(s, "");
        }
        String str = result.replace("#alanpoi#replace", "<#list dataList as record>" +
                "<si><t>${record}</t></si>" +
                "</#list>");
        Writer writer = new FileWriter(sharedStrings, false);
        writer.write(str);
        writer.flush();
        writer.close();
        Map<String, Object> param = new HashMap<>();
        param.put("dataList", replaceList);
        Template docTemplate = createTemplate(sharedStrings.getParent(), sharedStrings.getName(), true);
        docTemplate.process(param, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sharedStrings), "utf-8")));
        valList.addAll(replaceList);
        writeSheet(rowEntities, valList);
    }

    public void writeSheet(List<RowEntity> rowEntities, List<Object> valList) throws IOException, JDOMException, TemplateException {
        long beginRowIndex = -1;
        if (rowEntities != null && rowEntities.size() > 0) {
            beginRowIndex = rowEntities.get(0).getRowIndex();
        }
        File sheetFile = this.getEntity(ExcelZipPackage.ZIP_ENTRY_XL);
        Document doc = saxBuilder.build(sheetFile);

        if (beginRowIndex == -1) {
            //复杂处理
            AbstractConversion conversion = new ColorfulConversion(rowEntities, doc, valList, sheetFile);
            conversion.start(null);
            List<Hyperlink> hyperlinks = conversion.getHyperlinkList();
            if (hyperlinks != null && hyperlinks.size() > 0) {
                new WorkRelDocument(zipItem.get(ZIP_ENTRY_XL_REL)).write(hyperlinks);
            }
        } else {
            //标准处理
            AbstractConversion conversion = new StandardConversion(rowEntities, doc, valList, sheetFile);
            conversion.start((p, k) -> {
                Template docTemplate = null;
                try {
                    docTemplate = createTemplate(k.getParent(), k.getName(), true);
                    docTemplate.process(p, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sheetFile), "utf-8")));
                } catch (Exception e) {
                    throw new AlanPoiException(e);
                }

                return true;
            });
        }


    }

    private void composite() throws IOException {
        ZipFile originZf = null;
        ZipInputStream zipInputStream = null;
        ZipOutputStream zipOutputStream = null;
        InputStream sheetIs = null;
        InputStream sheetRelIs = null;
        InputStream sharedIs = null;
        try {
            // 原始压缩文件
            originZf = new ZipFile(inFile);
            //获取原始压缩文件流
            zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(inFile)));
            //生成新的文档
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath)));
            ZipEntry originZi = null;
            // 缓冲区
            byte[] buf = new byte[8 * 1024];
            int len;
            while ((originZi = zipInputStream.getNextEntry()) != null) {
                String entryName = originZi.getName();
                ZipEntry entryOut = new ZipEntry(entryName);
                zipOutputStream.putNextEntry(entryOut);
                if (entryName.startsWith(ZIP_ENTRY_XL)) {
                    sheetIs = new FileInputStream(zipItem.get(ZIP_ENTRY_XL));
                    // 使用替换流（替换图片）
                    while ((len = (sheetIs.read(buf))) > 0) {
                        zipOutputStream.write(buf, 0, len);
                    }

                } else if (entryName.startsWith(ZIP_ENTRY_XL_REL)) {
                    sheetRelIs = new FileInputStream(zipItem.get(ZIP_ENTRY_XL_REL));
                    // 使用替换流（替换图片）
                    while ((len = (sheetRelIs.read(buf))) > 0) {
                        zipOutputStream.write(buf, 0, len);
                    }

                } else if (entryName.startsWith(ZIP_ENTRY_SHARED_STRINGS)) {
                    sharedIs = new FileInputStream(zipItem.get(ZIP_ENTRY_SHARED_STRINGS));
                    // 使用替换流（替换图片）
                    while ((len = (sharedIs.read(buf))) > 0) {
                        zipOutputStream.write(buf, 0, len);
                    }

                } else {
                    while ((len = (zipInputStream.read(buf))) > 0) {
                        zipOutputStream.write(buf, 0, len);
                    }
                }
                // 关闭此 entry
                zipOutputStream.closeEntry();
            }
        } catch (IOException ioException) {
            throw ioException;
        } finally {
            CloseUtils.close(originZf);
            CloseUtils.close(sheetIs);
            CloseUtils.close(sharedIs);
            CloseUtils.close(sheetRelIs);
            CloseUtils.close(zipInputStream);
            CloseUtils.close(zipOutputStream);
        }
    }


    @Override
    public void complete() throws IOException {
        composite();
    }
}
