package test.analysis.excel;

import com.alanpoi.analysis.common.enums.AlanColor;
import com.alanpoi.analysis.excel.annotation.DateFormat;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.ExcelSheet;
import com.alanpoi.analysis.excel.annotation.NumFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ExcelSheet(name = "测试2", backColor = AlanColor.YELLOW, font = "宋体", index = 1, fontSize = 25)
@Data
public class Export2VO {
    @ExcelColumn(name = "名称", width = 32, index = "0", link = "http://alanpoi.com/${value}")
    private String name;

    @ExcelColumn(name = "值", index = "3")
    private String value;

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
