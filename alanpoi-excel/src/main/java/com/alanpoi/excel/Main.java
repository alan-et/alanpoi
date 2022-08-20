package com.alanpoi.excel;

import com.alanpoi.excel.common.ExportMultipleSheetParam;
import com.alanpoi.excel.common.utils.ExcelExportUtil;
import com.alanpoi.excel.exports.handle.TemplateHandle;
import com.alanpoi.excel.parse.ZipPackage;
import org.apache.poi.ss.usermodel.Workbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        test1();
        test2();
    }

    private static void test2() throws IOException {
        TemplateHandle templateHandle = new TemplateHandle();
        List<ExportVO> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            ExportVO exportVO = new ExportVO();
            if ((i > 100 && i < 103) || (i > 108 && i < 111)) {
                exportVO.setName("name");
            } else {
                exportVO.setName("name" + i);
            }
            exportVO.setValue("113343434343898888");
            exportVO.setAmount(new BigDecimal(6666.666 + i * 10));
            exportVO.setDate(new Date(132324343 + i * 100));
            exportVO.setDateTime(new java.util.Date());
            list.add(exportVO);
        }
        long begin = System.currentTimeMillis();
        ZipPackage zipPackage = templateHandle.getExcelStream("/Users/pengzhuoxun/Downloads/testTem.xlsx", list, ExportVO.class, 1);
        System.out.println("alanpoi 总耗时 " + (System.currentTimeMillis() - begin) + "毫秒");
        zipPackage.write(new FileOutputStream("/Users/pengzhuoxun/Downloads/test666.xlsx"));
    }

    private static void test1() throws IOException {
        List<ExportVO> list = new ArrayList<>();
        List<Export2VO> list2 = new ArrayList<>();
        BufferedImage bufferImg;
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        bufferImg = ImageIO.read(new File("/Users/pengzhuoxun/Downloads/image1.png"));

        ImageIO.write(bufferImg, "png", byteArrayOut);
        for (int i = 0; i < 1000000; i++) {
            ExportVO exportVO = new ExportVO();
            if ((i > 100 && i < 103) || (i > 108 && i < 111)) {
                exportVO.setName("name");
            } else {
                exportVO.setName("name" + i);
            }
            exportVO.setValue("113343434343898888");
            exportVO.setAmount(new BigDecimal(6666.666 + i * 10));
            exportVO.setDate(new Date(132324343 + i * 100));
            exportVO.setDateTime(new java.util.Date());
            list.add(exportVO);

//            Export2VO export2VO = new Export2VO();
//            export2VO.setName("name" + i);
//            export2VO.setValue("value" + i);
//            export2VO.setAmount(new BigDecimal(6666.666 + i * 10));
//            export2VO.setDate(new Date(132324343 + i * 100));
//            export2VO.setDateTime(new java.util.Date());
//            export2VO.setImage("https://t7.baidu.com/it/u=1569919947,316169633&fm=218&app=92&f=PNG?w=121&h=75&s=6ED4EC1A07384D8A0654DCDE03007031");
//            export2VO.setImage2(byteArrayOut.toByteArray());
//            list2.add(export2VO);
        }
        long begin = System.currentTimeMillis();
            Workbook workbook = ExcelExportUtil.getWorkbook(list, ExportVO.class);
        System.out.println("apache poi耗时 " + (System.currentTimeMillis() - begin) + "毫秒");
//        ExportMultipleSheetParam param = new ExportMultipleSheetParam();
//        param.put(0, "测试1", ExportVO.class, list);
//        param.put(1, "测试2", Export2VO.class, list2);
//        Workbook workbook = ExcelExportUtil.getByMultiSheet(param);
        OutputStream outputStream = new FileOutputStream("/Users/pengzhuoxun/Downloads/test.xlsx");
        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }
}
