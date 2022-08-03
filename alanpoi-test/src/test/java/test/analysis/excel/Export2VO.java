package test.analysis.excel;

import com.alanpoi.analysis.common.enums.AlanColor;
import com.alanpoi.analysis.common.enums.Align;
import com.alanpoi.analysis.common.enums.DataType;
import com.alanpoi.analysis.excel.annotation.DateFormat;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.ExcelSheet;
import com.alanpoi.analysis.excel.annotation.NumFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ExcelSheet(name = "测试2", backColor = AlanColor.YELLOW, font = "宋体", fontSize = 25)
@Data
public class Export2VO {
    @ExcelColumn(name = "名称", width = 32, height = 150, index = "0", align = Align.CENTER, link = "http://alanpoi.com/${value}")
    private String name;

    @ExcelColumn(name = "值", index = "3")
    private String value;

    @ExcelColumn(name = "图片", index = "5", width = 50, height = 100, type = DataType.IMAGE)
    private String image;

    @ExcelColumn(name = "图片2", index = "6", width = 50, height = 100, type = DataType.IMAGE)
    private byte[] image2;

    @NumFormat(value = "#0.00")
    @ExcelColumn(name = "金额", index = "2")
    private BigDecimal amount;

    @ExcelColumn(name = "时间格式化", index = "1")
    @DateFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;

    @DateFormat
    @ExcelColumn(name = "日期格式化", index = "4")
    private java.sql.Date date;
}
