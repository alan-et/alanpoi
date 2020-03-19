package com.alanpoi;

import com.alanpoi.excel.utils.ExcelExportUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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
                exportVO.setName("name"+i);
                exportVO.setValue("value"+i);
                exportVO.setAmount(new BigDecimal(6666.666+i*10));
                exportVO.setDate(new Date(132324343+i*100));
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
