package com.alanpoi.pdf;

import com.alanpoi.common.util.CloseUtils;
import com.alanpoi.common.util.FileUtils;
import com.alanpoi.common.util.StringUtils;
import com.alanpoi.pdf.event.AlPdfPageEvent;
import com.alanpoi.xml.AbstractParse;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfWriter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * PDF 转换器
 *
 * @author Alan
 * @since 1.3.5
 */
public class PdfConvert extends AbstractParse {

    //正文内容
    private String content;

    //封面
    private String cover;

    //水印图片地址
    private String waterUrl;

    private PdfWriter _writer;

    private ArrayList<PdfPageEvent> _events = new ArrayList<>();

    private ITextRenderer _renderer;

    private ITextFontResolver _fontResolver;

    private String tmpPath;


    private static final String CONTENT_PATH = "pdf/content.ftl";
    private static final String COVER_PATH = "pdf/cover.ftl";
    private static final String WATER_PATH = "pdf/image/water.png";

    public PdfConvert(String templatePath, Map<String, Object> templateParam) throws IOException, TemplateException {
        init();
        if (templatePath.endsWith(".tpl") || templatePath.endsWith(".zip")) {
            File file = new File(templatePath);
            if (!file.exists()) {
                //获取项目相对路径资源
                InputStream is = getClass().getClassLoader().getResourceAsStream(templatePath);
                file = FileUtils.saveTempFile(is, "pdf", ".zip", FileUtils.getTmpDir());
                tmpPath = file.getPath();
            }
            loadZip(file, templateParam);
        } else {
            String[] pathArr = templatePath.split("/");
            String fileName = pathArr[pathArr.length - 1];
            String parentPath = templatePath.replace(fileName, "");
            Template template = createTemplate(parentPath, fileName, false);
            StringWriter writer = new StringWriter();
            template.process(templateParam, writer);
            content = writer.toString();
        }
    }

    public PdfConvert(InputStream templateIs, Map<String, Object> templateParam, boolean isZip) throws IOException, TemplateException {
        init();
        if (isZip) {
            File file = FileUtils.saveTempFile(templateIs, "pdf", ".zip", FileUtils.getTmpDir());
            loadZip(file, templateParam);
            return;
        } else {
            File file = FileUtils.saveTempFile(templateIs, "pdf", ".ftl", FileUtils.getTmpDir() + "template");
            tmpPath = file.getPath();
            Template template = createTemplate(file.getParent(), file.getName(), true);
            StringWriter writer = new StringWriter();
            template.process(templateParam, writer);
            content = writer.toString();
        }
    }

    public static PdfConvert getInstance(String templatePath, Map<String, Object> templateParam) throws IOException, TemplateException {
        return new PdfConvert(templatePath, templateParam);
    }


    public void init() {
        _renderer = new ITextRenderer();
        _writer = _renderer.getWriter();
        _fontResolver = _renderer.getFontResolver();
        configure = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configure.setDefaultEncoding("utf-8");
    }

    /**
     * 加载压缩文件
     *
     * @param file
     * @param templateParam
     * @throws TemplateException
     * @throws IOException
     */
    private void loadZip(File file, Map<String, Object> templateParam) throws TemplateException, IOException {
        ZipFile origin_zf = null;
        InputStream temp_content_is = null;
        InputStream temp_cover_is = null;
        ZipInputStream zipInputStream = null;
        File contentFtl = null;
        File coverFtl = null;
        StringWriter writer_content = null;
        StringWriter writer_cover = null;
        try {
            // 原始压缩文件
            origin_zf = new ZipFile(file);
            //获取原始压缩文件流
            zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            ZipEntry originZi = null;
            while ((originZi = zipInputStream.getNextEntry()) != null) {
                String entryName = originZi.getName();
                if (entryName.endsWith(CONTENT_PATH)) {
                    //获取原始压缩文件
                    temp_content_is = origin_zf.getInputStream(originZi);
                    contentFtl = FileUtils.saveTempFile(temp_content_is, "pdf-document", ".ftl", FileUtils.getTmpDir() + "template");
                    //获取模版
                    Template template_content = createTemplate(contentFtl.getParent(), contentFtl.getName(), true);
                    //模版填充
                    writer_content = new StringWriter();
                    template_content.process(templateParam, writer_content);
                    content = writer_content.toString();

                } else if (entryName.endsWith(COVER_PATH)) {
                    //获取封面布局
                    temp_cover_is = origin_zf.getInputStream(originZi);
                    coverFtl = FileUtils.saveTempFile(temp_cover_is, "pdf-cover", ".ftl", FileUtils.getTmpDir() + "template");
                    //获取封面模版
                    Template coverTemplate = createTemplate(coverFtl.getParent(), coverFtl.getName(), true);
                    //模版填充
                    writer_cover = new StringWriter();
                    coverTemplate.process(templateParam, writer_cover);
                    cover = writer_cover.toString();
                } else if (entryName.endsWith(WATER_PATH)) {
                    InputStream water_is = origin_zf.getInputStream(originZi);
                    File waterFile = FileUtils.saveTempFile(water_is, "pdf-cover", ".ftl", FileUtils.getTmpDir() + "template");
                    waterUrl = waterFile.getAbsolutePath();
//                    FileUtils.deleteTempFileOnExit(waterFile);
                }
            }
        } catch (TemplateException templateException) {
            throw templateException;
        } catch (IOException ioException) {
            throw ioException;
        } finally {
            CloseUtils.close(origin_zf);
            CloseUtils.close(temp_content_is);
            CloseUtils.close(temp_cover_is);
            CloseUtils.close(zipInputStream);
            CloseUtils.close(writer_content);
            CloseUtils.close(writer_cover);
            FileUtils.deleteTempFile(contentFtl);
            FileUtils.deleteTempFile(coverFtl);
        }
    }

    public void setPageEvent(PdfPageEvent event) {
        _events.add(event);
    }

    public PdfWriter getWriter() {
        return _writer;
    }

    public ITextFontResolver getFontResolver() {
        return _fontResolver;
    }

    public void createPdf(OutputStream outputStream) throws IOException, DocumentException {
        //设置默认字体
        _fontResolver.addFont("fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        _fontResolver.addFont("fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        _renderer.getSharedContext().setReplacedElementFactory(new Base64ImgReplacedElementFactory());
        if (StringUtils.isNotBlank(cover)) {
            _renderer.setDocumentFromString(cover);
            _renderer.layout();
            _renderer.createPDF(outputStream, false);
        }
        if (StringUtils.isNotBlank(waterUrl)) {
            _renderer.getWriter().setPageEvent(new AlPdfPageEvent(waterUrl));
        }
        for (PdfPageEvent event : _events) {
            _renderer.getWriter().setPageEvent(event);
        }
        _renderer.setDocumentFromString(content);
        _renderer.layout();
        _renderer.writeNextDocument();
        try {
//            renderer.createPDF(outputStream);
            _renderer.finishPDF();
        } finally {
            outputStream.close();
            File tmp = new File(tmpPath);
            if (tmp.exists()) {
                tmp.delete();
            }
            if (StringUtils.isNotBlank(waterUrl)) {
                File waterTmp = new File(waterUrl);
                if (waterTmp.exists()) {
                    waterTmp.delete();
                }
            }
        }
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getWaterUrl() {
        return waterUrl;
    }

    public void setWaterUrl(String waterUrl) {
        this.waterUrl = waterUrl;
    }
}
