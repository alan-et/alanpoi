package test.analysis.excel;

import com.alanpoi.analysis.excel.utils.ExcelExportUtil;
import com.alanpoi.common.util.HttpUtils;
import com.alanpoi.test.Application;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@SpringBootTest(classes = Application.class)
class AnalysisTest {

    @Test
    void testExport() {
        try {
            List<ExportVO> list = new ArrayList<>();
            List<Export2VO> list2 = new ArrayList<>();
            for (int i = 0; i < 500; i++) {
                ExportVO exportVO = new ExportVO();
                exportVO.setName("name" + i);
//                exportVO.setValue(new BigDecimal(1111181.11 + i * 0.09));
                exportVO.setValue("113343434343898888");
                exportVO.setAmount(new BigDecimal(6666.666 + i * 10));
                exportVO.setDate(new Date(132324343 + i * 100));
                exportVO.setDateTime(new java.util.Date());
                list.add(exportVO);

                Export2VO export2VO = new Export2VO();
                export2VO.setName("name" + i);
                export2VO.setValue("value" + i);
                export2VO.setAmount(new BigDecimal(6666.666 + i * 10));
                export2VO.setDate(new Date(132324343 + i * 100));
                export2VO.setDateTime(new java.util.Date());
                list2.add(export2VO);
            }
//            Workbook workbook = ExcelExportUtil.getWorkbook(list, ExportVO.class);
            Map<Class<?>, Collection<?>> map = new HashMap<>();
            map.put(ExportVO.class, list);
            map.put(Export2VO.class, list2);
            Workbook workbook = ExcelExportUtil.getWorkbookByMultiSheet(map);
            OutputStream outputStream = new FileOutputStream("/tmp/test.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

        } finally {

        }
    }

    @Test
    public void testSpecifyCol() {
        try {
            List<ExportVO> list = new ArrayList<>();
            for (int i = 0; i < 500; i++) {
                ExportVO exportVO = new ExportVO();
                exportVO.setName("name" + i);
//                exportVO.setValue(new BigDecimal(123.11 + i * 0.09));
                exportVO.setAmount(new BigDecimal(6666.666 + i * 10));
                exportVO.setDate(new Date(132324343 + i * 100));
                exportVO.setDateTime(new java.util.Date());
                list.add(exportVO);
            }
            List<String> colList = new ArrayList<>();
            colList.add("value");
            colList.add("name");
            Workbook workbook = ExcelExportUtil.getWorkbookSpecifyCol(list, ExportVO.class, colList);
            OutputStream outputStream = new FileOutputStream("/tmp/test1.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

        } finally {

        }
    }

}
