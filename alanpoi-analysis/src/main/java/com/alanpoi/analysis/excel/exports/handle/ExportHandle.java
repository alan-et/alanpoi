package com.alanpoi.analysis.excel.exports.handle;

import com.alanpoi.analysis.excel.annotation.DateFormat;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.ExcelSheet;
import com.alanpoi.analysis.excel.annotation.NumFormat;
import com.alanpoi.analysis.excel.exports.ExcelParseParam;
import com.alanpoi.analysis.excel.exports.ReflectorManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExportHandle {
    protected static final Logger logger = LoggerFactory.getLogger(ExportHandle.class);

    public Workbook exportData(Workbook workbook, Collection<?> data, Class<?> c) {
        try {
            loadSheet(workbook, data, c, 0);
        } catch (Exception e) {
            logger.error("", e);
        }
        return workbook;
    }

    public Workbook exportMultipleSheet(Workbook workbook, Map<Class<?>, Collection<?>> dataMap) {
        int sheetAt = 0;
        for (Class<?> c : dataMap.keySet()) {
            Collection<?> collection = dataMap.get(c);
            loadSheet(workbook, collection, c, sheetAt++);
        }
        return workbook;
    }

    private void loadSheet(Workbook workbook, Collection<?> data, Class<?> c, int sheetAt) {
        CellStyle headStyle = workbook.createCellStyle();
        headStyle.setWrapText(true);
        ReflectorManager reflectorManager = ReflectorManager.fromCache(c);
        ExcelSheet excelSheet = c.getAnnotation(ExcelSheet.class);
        int rowInd = 0;
        Sheet sheet = null;
        Row headRow = null;
        if (excelSheet != null) {
            if (excelSheet.index() < sheetAt) {
                sheet = workbook.getSheetAt(sheetAt);
                workbook.setSheetName(sheetAt, excelSheet.name());
            } else {
                sheet = workbook.getSheetAt(excelSheet.index());
                workbook.setSheetName(excelSheet.index(), excelSheet.name());
            }
            headRow = sheet.createRow(rowInd);
            headRow.setHeightInPoints(excelSheet.rowHeight());
            Font font = workbook.createFont();
            font.setFontName(excelSheet.font());
            font.setFontHeightInPoints((short) excelSheet.fontSize());//设置字体大小
            headStyle.setFont(font);
            headStyle.setAlignment(HorizontalAlignment.CENTER);
            headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headStyle.setFillForegroundColor(excelSheet.backColor().index);
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        } else {
            sheet = workbook.getSheetAt(sheetAt);
            logger.warn("请在导出类上加上注解，以便导出文件更加完整");
        }
        List<Field> fields = reflectorManager.getFieldList();
        int fieldLength = fields.size();
        List<ExcelParseParam> excelParseParamList = new ArrayList<>();
        for (int i = 0; i < fieldLength; i++) {
            ExcelColumn excelColumn = fields.get(i).getAnnotation(ExcelColumn.class);
            DateFormat dateFormat = fields.get(i).getAnnotation(DateFormat.class);
            NumFormat numFormat = fields.get(i).getAnnotation(NumFormat.class);
            ExcelParseParam excelParseParam = new ExcelParseParam();
            if (excelColumn != null) {
                if (!excelColumn.isExist()) continue;
                Cell cell;
                if (StringUtils.isNotBlank(excelColumn.index())) {
                    cell = headRow.createCell(Integer.valueOf(excelColumn.index()));
                    excelParseParam.setIndex(Integer.valueOf(excelColumn.index()));
                } else {
                    cell = headRow.createCell(i);
                }
                cell.setCellValue(excelColumn.name());
                cell.setCellStyle(headStyle);
                sheet.setColumnWidth(i, excelColumn.width() * 256);
                excelParseParam.setHeight(excelColumn.height());
                excelParseParam.setColor(excelColumn.color().index);
                excelParseParam.setCellStyle(workbook.createCellStyle());
            } else {
                Cell cell = headRow.createCell(i);
                cell.setCellValue(fields.get(i).getName());
                cell.setCellStyle(headStyle);
            }

            if (dateFormat != null) {
                excelParseParam.setFormat(dateFormat.value());
            }
            if (numFormat != null) {
                excelParseParam.setNumFormat(numFormat.value());
            }
            excelParseParam.setMethod(reflectorManager.getGetMethod(fields.get(i).getName()));
            excelParseParamList.add(excelParseParam);
        }
        rowInd++;
        Iterator iterator = data.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            handleRow(sheet.createRow(rowInd), object, excelParseParamList);
            rowInd++;
        }
        logger.info("excel sheet({}) handle completed !", sheet.getSheetName());
    }

    /**
     * handle excel row
     *
     * @param row
     * @param object
     * @param excelParseParams
     */
    private void handleRow(Row row, Object object, List<ExcelParseParam> excelParseParams) {
        if (CollectionUtils.isEmpty(excelParseParams)) {
            return;
        }
        row.setHeightInPoints(excelParseParams.get(0).getHeight());
        for (int j = 0; j < excelParseParams.size(); j++) {
            Integer index = excelParseParams.get(j).getIndex();
            handleCell(row.createCell(index != null ? index : j), object, excelParseParams.get(j));
        }
    }

    private void handleCell(Cell cell, Object object, ExcelParseParam excelParseParam) {
        if (object instanceof Map) {

        } else {
            try {
                Object value = excelParseParam.getMethod().invoke(object);
                boolean isFormat = false;
                if (StringUtils.isNotEmpty(excelParseParam.getFormat())) {
                    value = this.dateFormatValue(value, excelParseParam);
                    isFormat = true;
                } else if (StringUtils.isNotEmpty(excelParseParam.getNumFormat())) {
                    value = this.numFormatValue(value, excelParseParam);
                    isFormat = true;
                }
                if (!isFormat) {
                    if (value == null) cell.setCellValue("");
                    else if (NumberUtils.isCreatable(value.toString()))
                        if (value.toString().indexOf(".") != -1)
                            cell.setCellValue(Double.valueOf(value.toString()));
                        else
                            cell.setCellValue(Long.valueOf(value.toString()));
                    else
                        cell.setCellValue(value.toString());

                } else {
                    cell.setCellValue(value == null ? "" : value.toString());
                }

                cell.setCellStyle(excelParseParam.getCellStyle());
            } catch (IllegalAccessException e) {
                logger.error("", e);
            } catch (InvocationTargetException e) {
                logger.error("", e);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }

    private Object dateFormatValue(Object value, ExcelParseParam parseParam) throws Exception {
        Date temp = null;
        SimpleDateFormat format;
        if (value instanceof Date) {
            temp = (Date) value;
        } else if (value instanceof java.sql.Date) {
            temp = new Date(((java.sql.Date) value).getTime());
        } else if (value instanceof Time) {
            temp = new Date(((Time) value).getTime());
        } else if (value instanceof Timestamp) {
            temp = new Date(((Timestamp) value).getTime());
        }

        if (temp != null) {
            format = new SimpleDateFormat(parseParam.getFormat());
            value = format.format(temp);
        }

        return value;
    }

    private Object numFormatValue(Object value, ExcelParseParam parseParam) {
        if (value == null) {
            return null;
        } else if (!NumberUtils.isNumber(value.toString())) {
            logger.error("Data format error:" + value);
            return null;
        } else {
            Double d = Double.parseDouble(value.toString());
            DecimalFormat df = new DecimalFormat(parseParam.getNumFormat());
            return df.format(d);
        }
    }
}
