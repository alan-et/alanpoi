package com.alanpoi.analysis.pdf.event;

import com.alanpoi.common.util.FileUtils;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * PDF 分页事件处理
 *
 * @author Alan
 * @since 1.3.5
 */
public class AlPdfPageEvent extends PdfPageEventHelper {

    private String watermarkImgPath;

    public AlPdfPageEvent(String watermarkImgPath) {
        this.watermarkImgPath = watermarkImgPath;
    }

    private static Logger log = LoggerFactory.getLogger(AlPdfPageEvent.class);

    @SneakyThrows
    @Override
    public void onEndPage(PdfWriter pdfWriter, Document document) {
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.5f);
        PdfContentByte content = pdfWriter.getDirectContentUnder();
        content.beginText();
        content.setGState(gs);
        Image image;
        File file = new File(watermarkImgPath);
        if (!file.exists()) {
            //获取项目相对路径资源
            URL url = getClass().getClassLoader().getResource(watermarkImgPath);
            image = Image.getInstance(url);
        } else {
            image = Image.getInstance(watermarkImgPath);
        }
        image.setAbsolutePosition(0.0F, 0.0F);
        image.scaleAbsoluteWidth(pdfWriter.getPageSize().getWidth());
        image.scaleAbsoluteHeight(pdfWriter.getPageSize().getHeight());
        content.addImage(image);
        // 设置水印颜色(灰色)
        content.setColorFill(Color.GRAY);
        //结束
        content.endText();
    }
}