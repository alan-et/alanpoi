package com.alanpoi;

import com.alanpoi.excel.utils.ExcelExportUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AlanpoiApplicationTests {

    @Test
    void testExport() {
        try {
            List<ExportVO> list = new ArrayList<>();
            for (int i=0;i<500;i++){
                ExportVO exportVO = new ExportVO();
                exportVO.setName("123");
                exportVO.setValue("32143放的");
                exportVO.setDate(new Date(1232323123));
                exportVO.setDateTime(new java.util.Date());
                list.add(exportVO);
            }
            Workbook workbook = ExcelExportUtil.getWorkbook(list, ExportVO.class);
            OutputStream outputStream = new FileOutputStream("/tmp/test.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

        } finally {

        }
    }

}
