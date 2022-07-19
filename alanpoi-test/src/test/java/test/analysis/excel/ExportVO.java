package test.analysis.excel;

import com.alanpoi.analysis.common.enums.AlanColor;
import com.alanpoi.analysis.excel.annotation.DateFormat;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.ExcelSheet;
import com.alanpoi.common.annotation.NumFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ExcelSheet(name = "测试问额", backColor = AlanColor.BLUE, font = "黑体",fontSize = 25)
@Data
public class ExportVO {
    @ExcelColumn(name = "名称", width = 60)
    private String name;

    @ExcelColumn(name = "值")
    @NumFormat(value = "#0.#")
    private String  value;

    @ExcelColumn(name = "金额", color = AlanColor.RED)
    @NumFormat(value = "#0.00%")
    private BigDecimal amount;

    @ExcelColumn(name = "时间格式化")
    @DateFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;

    @DateFormat
    @ExcelColumn(name = "日期格式化",isExist = false)
    private java.sql.Date date;
}
