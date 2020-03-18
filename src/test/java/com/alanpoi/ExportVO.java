package com.alanpoi;

import com.alanpoi.excel.annotation.DateFormat;
import com.alanpoi.excel.annotation.ExcelColumn;
import com.alanpoi.excel.annotation.ExcelSheet;
import lombok.Data;

import java.util.Date;

@ExcelSheet(name = "测试")
@Data
public class ExportVO {
    @ExcelColumn(name = "名称")
    private String name;
    @ExcelColumn(name = "值")
    private String value;

    @ExcelColumn(name = "时间格式化")
    @DateFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;
    @DateFormat
    @ExcelColumn(name = "日期格式化")
    private java.sql.Date date;
}
