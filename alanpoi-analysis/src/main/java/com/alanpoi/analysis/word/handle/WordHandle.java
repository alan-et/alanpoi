package com.alanpoi.analysis.word.handle;

import com.alanpoi.analysis.common.ReflectorManager;
import com.alanpoi.analysis.word.*;
import com.alanpoi.analysis.word.annotation.WordField;
import com.alanpoi.common.annotation.DateFormat;
import com.alanpoi.common.annotation.NumFormat;
import com.alanpoi.common.util.StringUtils;
import com.google.code.appengine.awt.Color;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.itext.extension.font.IFontProvider;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class WordHandle {
    private final static Logger logger = LoggerFactory.getLogger(WordHandle.class);

    protected WordParse wordParse;

    public WordHandle() {
        this.wordParse = new DocxParse();
    }

    public WordHandle(boolean isDocx) {
        if (isDocx) this.wordParse = new DocxParse();
        else this.wordParse = new DocParse();

    }

    public void setWord2003() {
        this.wordParse = new DocParse();
    }

    public IWordWorkbook getWorkbook(String templatePath, Map<String, Object> param) throws IOException {
        return wordParse.createDoc(templatePath, param);
    }

    public IWordWorkbook getWorkbookByDocx(String templatePath, Map<String, Object> param, List<Media> mediaList) throws IOException {
        return wordParse.createDoc(templatePath, param, mediaList);
    }

    public IWordWorkbook getWorkbook(Class<?> c, Object data) throws IOException {
        ReflectorManager reflectorManager = ReflectorManager.fromCache(c);
        List<Field> fields = reflectorManager.getFieldList();
        int fieldLength = fields.size();
        List<WordEntity> wordEntityList = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < fieldLength; i++) {
            Field field = fields.get(i);
            WordField wordField = fields.get(i).getAnnotation(WordField.class);
            DateFormat dateFormat = fields.get(i).getAnnotation(DateFormat.class);
            NumFormat numFormat = fields.get(i).getAnnotation(NumFormat.class);
            if (!wordField.isExist()) continue;
            WordEntity wordEntity = new WordEntity();
            wordEntity.setAlign(wordField.align());
            wordEntity.setColor(wordEntity.getColor());
            wordEntity.setHighlight(wordField.highlight());
            wordEntity.setLink(wordField.link());
            wordEntity.setName(wordField.name());
            wordEntity.setpStyle(wordField.pStyle());
            wordEntity.setrStyle(wordField.rStyle());
            if (StringUtils.isNotBlank(wordField.index())) {
                wordEntity.setIndex(wordField.index());
            } else {
                wordEntity.setIndex(String.valueOf(index));
            }
            try {
                wordEntity.setValue((String) reflectorManager.getGetMethod(field.getName()).invoke(data));
            } catch (IllegalAccessException e) {
                logger.error("", e);
            } catch (InvocationTargetException e) {
                logger.error("", e);
            }
            wordEntityList.add(wordEntity);
            index++;
        }
        return wordParse.createDoc(wordEntityList, null);
    }

    public HtmlHandle getHtmlHandle(boolean isDocx) throws IOException {
        return new HtmlHandle(isDocx);
    }

    private IWordWorkbook export(Class<?> c, Collection<?> data) {
        //TODO
        return null;
    }

    public void wordConverterToPdf(InputStream source, OutputStream target,
                                   PdfOptions options,
                                   Map<String, String> params) throws IOException {
        options.fontEncoding("UTF-8");
        options.fontProvider(new IFontProvider() {
            @Override
            public Font getFont(String s, String s1, float v, int i, Color color) {
                try {
                    BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
                    Font font = new Font(baseFont, v, i, color);
                    if (s != null) {
                        font.setFamily(s);
                    }
                    return font;
                } catch (Exception e) {
                    logger.warn("", e);
                    return null;
                }

            }
        });
        XWPFDocument doc = new XWPFDocument(source);
        PdfConverter.getInstance().convert(doc, target, options);
    }

    /**
     * 基于html的处理类
     */
    static class HtmlHandle extends WordHandle {
        private File htmlFile;

        public HtmlHandle(boolean isDocx) throws IOException {
            this.htmlFile = File.createTempFile(UUID.randomUUID().toString(), ".ftl");
            if (isDocx) this.wordParse = new DocxParse();
            else this.wordParse = new DocParse();
        }

        protected synchronized boolean addContent(String part) throws IOException {
            FileWriter writer = null;
            BufferedWriter bos = null;
            try {
                writer = new FileWriter(htmlFile, true);
                bos = new BufferedWriter(writer);
                bos.write(part);
            } catch (IOException e) {
                throw new IOException(e);
            } finally {
                if (bos != null) bos.close();
                if (writer != null) writer.close();
            }
            return true;
        }

        public IWordWorkbook getWorkbook(File tempFile, Map<String, Object> param) throws IOException {
            IWordWorkbook workbook = wordParse.createDoc(tempFile, param, true);
            tempFile.delete();
            return workbook;
        }
    }
}
