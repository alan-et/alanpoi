package com.alanpoi;

import com.alanpoi.common.AlanColor;
import com.alanpoi.excel.annotation.DateFormat;
import com.alanpoi.excel.annotation.ExcelColumn;
import com.alanpoi.excel.annotation.ExcelSheet;
import com.alanpoi.excel.annotation.NumFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ExcelSheet(name = "测试", backColor = AlanColor.GREEN, font = "宋体", fontSize = 25)
@Data
public class ExportVO {
    @ExcelColumn(name = "名称", width = 32, index = "0")
    private String name;

    @ExcelColumn(name = "值", index = "3")
    private String value;

    @ExcelColumn(name = "金额",color = AlanColor.RED)
    @NumFormat(value = "0000.00##")
    private BigDecimal amount;

    @ExcelColumn(name = "时间格式化", index = "1")
    @DateFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;

    @DateFormat
    @ExcelColumn(name = "日期格式化", index = "4")
    private java.sql.Date date;
}
