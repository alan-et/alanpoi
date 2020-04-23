package com.alanpoi.analysis;

import com.alanpoi.analysis.common.AlanColor;
import com.alanpoi.analysis.excel.annotation.DateFormat;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.ExcelSheet;
import com.alanpoi.analysis.excel.annotation.NumFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ExcelSheet(name = "测试请问额", backColor = AlanColor.BLUE, font = "黑体",fontSize = 25)
@Data
public class ExportVO {
    @ExcelColumn(name = "名称", width = 60, index = "0")
    private String name;

    @ExcelColumn(name = "值", index = "3")
    private String value;

    @ExcelColumn(name = "金额", color = AlanColor.RED, index = "2")
    @NumFormat(value = "0000.00##")
    private BigDecimal amount;

    @ExcelColumn(name = "时间格式化", index = "1")
    @DateFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;

    @DateFormat
    @ExcelColumn(name = "日期格式化", index = "4")
    private java.sql.Date date;
}
