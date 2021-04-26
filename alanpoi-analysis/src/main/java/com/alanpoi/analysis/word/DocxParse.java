package com.alanpoi.analysis.word;

import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.common.util.FileUtils;
import com.alanpoi.common.util.ID;
import com.alanpoi.common.util.StringUtils;
import freemarker.template.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author pengzhuoxun
 * @since 1.3.4
 */
public class DocxParse extends WordParse {
    private final static Logger logger = LoggerFactory.getLogger(DocxParse.class);

    private static final String DOCUMENT_PATH = "word/document.xml";
    private static final String DOCUMENT_REL_PATH = "word/_rels/document.xml.rels";

    public DocxParse() {

        configure = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configure.setDefaultEncoding("utf-8");

    }

    public void close(Closeable closeable) throws IOException {
        if (null != closeable) {
            try {
                if (closeable instanceof OutputStream) {
                    ((OutputStream) closeable).flush();
                }
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public IWordWorkbook createDoc(String templatePath, Map<String, Object> dataMap) throws IOException {
        return createDoc(templatePath, dataMap, null);
    }

    public IWordWorkbook createDoc(String zipTemplatePath, Map<String, Object> dataMap, List<Media> mediaList) throws IOException {
        String zipPath = getTmpDir() + ID.getId().next() + ".zip";
        File file = new File(zipTemplatePath);
        if (!file.exists()) {
            //获取项目相对路径资源
            InputStream is = getClass().getClassLoader().getResourceAsStream(zipTemplatePath);
            file = FileUtils.saveTempFile(is, "word", ".zip", getTmpDir());
        }
        ZipFile origin_zf = null;
        InputStream origin_docIs = null;
        InputStream origin_docRelIs = null;
        ZipInputStream zipInputStream = null;
        ZipOutputStream zipOutputStream = null;
        FileInputStream docRelIs = null;
        FileInputStream docIs = null;
        File documentFtl = null;
        File documentRelFtl = null;
        try {
            // 原始压缩文件
            origin_zf = new ZipFile(file);
            //获取原始压缩文件流
            zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            //生成新的文档
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath)));
            ZipEntry originZi = null;
            while ((originZi = zipInputStream.getNextEntry()) != null) {
                String entryName = originZi.getName();
                ZipEntry entryOut = new ZipEntry(entryName);
                // 只使用 name
                zipOutputStream.putNextEntry(entryOut);
                // 缓冲区
                byte[] buf = new byte[8 * 1024];
                int len;
                if (entryName.endsWith(DOCUMENT_PATH)) {
                    //获取原始压缩文件 文档文件
                    origin_docIs = origin_zf.getInputStream(originZi);
                    documentFtl = FileUtils.saveTempFile(origin_docIs, "document", ".ftl", getTmpDir() + "template");
                    //获取文档模版
                    Template docTemplate = createTemplate(documentFtl.getParent(), documentFtl.getName(), true);
                    //模版填充
                    docTemplate.process(dataMap, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(documentFtl), "utf-8")));
                    docIs = new FileInputStream(documentFtl);
                    // 使用替换流（替换文字内容）
                    while ((len = (docIs.read(buf))) > 0) {
                        zipOutputStream.write(buf, 0, len);
                    }
                } else if (entryName.endsWith(DOCUMENT_REL_PATH)) {
                    //获取原始压缩文件 文档关系文件
                    origin_docRelIs = origin_zf.getInputStream(originZi);
                    documentRelFtl = FileUtils.saveTempFile(origin_docRelIs, "d-rel", ".ftl", getTmpDir() + "template");
                    //获取文档关系模版
                    Template docRelTemplate = createTemplate(documentRelFtl.getParent(), documentRelFtl.getName(), true);
                    //模版填充
                    docRelTemplate.process(dataMap, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(documentRelFtl), "utf-8")));
                    docRelIs = new FileInputStream(documentRelFtl);
                    // 使用替换流（替换图片）
                    while ((len = (docRelIs.read(buf))) > 0) {
                        zipOutputStream.write(buf, 0, len);
                    }
                } else {
                    // 输出普通Zip流
                    while ((len = (zipInputStream.read(buf))) > 0) {
                        zipOutputStream.write(buf, 0, len);
                    }
                }
                // 关闭此 entry
                zipOutputStream.closeEntry();
            }
            //添加媒介
            addMedia(zipOutputStream, mediaList);
        } catch (TemplateException templateException) {
            throw new AlanPoiException(templateException.toString());
        } catch (IOException ioException) {
            throw new IOException(ioException);
        } finally {
            close(origin_zf);
            close(origin_docIs);
            close(origin_docRelIs);
            close(origin_docIs);
            close(origin_docRelIs);
            close(docIs);
            close(docRelIs);
            close(zipInputStream);
            close(zipOutputStream);
            FileUtils.deleteTempFile(documentFtl);
            FileUtils.deleteTempFile(documentRelFtl);
        }
        IWordWorkbook workbook = new XWordWorkbook(zipPath);
        return workbook;
    }

    private void addMedia(ZipOutputStream zipOutputStream, List<Media> mediaList) {
        if (mediaList == null || mediaList.size() == 0) return;
        mediaList.forEach(e -> {
            if (e == null) return;
            InputStream stream = null;
            try {
                zipOutputStream.putNextEntry(new ZipEntry("word/media/" + e.getFileName() + e.getFileType()));
                if (StringUtils.isNotBlank(e.getBase64())) {
                    byte[] bytes = Base64.decodeBase64(e.getBase64());
                    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                    byte[] buffer = new byte[1024];
                    int byteread = 0;
                    while ((byteread = in.read(buffer)) != -1) {
                        zipOutputStream.write(buffer, 0, byteread); //文件写操作
                    }
                } else {
                    URL url = new URL(e.getUrl());
                    stream = url.openStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = stream.read(buffer)) != -1) {
                        zipOutputStream.write(buffer, 0, length);
                    }

                    zipOutputStream.closeEntry();
                    stream.close();
                }
            } catch (IOException ioException) {
                logger.error("", ioException);
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException ioException) {
                        logger.error("", ioException);
                    }
                }

            }

        });

    }
}
