package com.alanpoi.word.handle;

import com.alanpoi.word.*;
import com.alanpoi.word.annotation.WordField;
import com.alanpoi.common.annotation.DateFormat;
import com.alanpoi.common.annotation.NumFormat;
import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.common.util.ReflectorManager;
import com.alanpoi.common.util.StringUtils;
import com.alanpoi.xml.common.Media;
import org.apache.fop.apps.FOUserAgent;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.convert.out.fo.renderers.FORendererApacheFOP;
import org.docx4j.fonts.BestMatchingMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class WordHandle {
    private final static Logger logger = LoggerFactory.getLogger(WordHandle.class);

    protected WordParse wordParse;

    protected boolean saveFO;

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

    public void setSaveFO(Boolean bool) {
        this.saveFO = bool;
    }

    public IWordWorkbook getWorkbook(String templatePath, Map<String, Object> param) throws IOException {
        return wordParse.createDoc(templatePath, param);
    }

    public IWordWorkbook getWorkbook2007(String templatePath, Map<String, Object> param, List<Media> mediaList) throws IOException {
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
                logger.warn("", e);
            } catch (InvocationTargetException e) {
                logger.warn("", e);
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


    /**
     * word文档转换为PDF
     *
     * @param docx docx文档
     * @param os   PDF文档存储路径
     * @throws AlanPoiException
     */
    public void wordToPDF(InputStream docx, OutputStream os) throws AlanPoiException {
        try {
            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(docx);
            Mapper fontMapper = new BestMatchingMapper();
            //中文字体转换
            fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
            fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
            fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
            fontMapper.put("微软雅黑", PhysicalFonts.get("Microsoft Yahei"));
            fontMapper.put("黑体", PhysicalFonts.get("SimHei"));
            fontMapper.put("楷体", PhysicalFonts.get("KaiTi"));
            fontMapper.put("新宋体", PhysicalFonts.get("NSimSun"));
            fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
            fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
            fontMapper.put("宋体扩展", PhysicalFonts.get("simsun-extB"));
            fontMapper.put("仿宋", PhysicalFonts.get("FangSong"));
            fontMapper.put("仿宋_GB2312", PhysicalFonts.get("FangSong_GB2312"));
            fontMapper.put("幼圆", PhysicalFonts.get("YouYuan"));
            fontMapper.put("华文宋体", PhysicalFonts.get("STSong"));
            fontMapper.put("华文中宋", PhysicalFonts.get("STZhongsong"));
            fontMapper.put("等线", PhysicalFonts.get("DengXian Regular"));
            fontMapper.put("Source Han Sans CN", PhysicalFonts.get("Source Han Serif Simplified Chinese"));
            mlPackage.setFontMapper(fontMapper);

            FOSettings foSettings = Docx4J.createFOSettings();
            if (saveFO) {
                foSettings.setFoDumpFile(File.createTempFile("word2pdf", ".fo"));
            }
            foSettings.setOpcPackage(mlPackage);
            FOUserAgent foUserAgent = FORendererApacheFOP.getFOUserAgent(foSettings);
            foUserAgent.setTitle("Alan-poi");
            Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
//            Docx4J.toPDF(mlPackage, os);
        } catch (Exception e) {
            throw new AlanPoiException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(os);
        }
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
