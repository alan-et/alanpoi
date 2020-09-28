package com.alanpoi.analysis.excel.exports.handle;

import com.alanpoi.analysis.common.AlanColor;
import com.alanpoi.analysis.excel.annotation.DateFormat;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.ExcelSheet;
import com.alanpoi.analysis.excel.annotation.NumFormat;
import com.alanpoi.analysis.excel.exports.ExcelParseParam;
import com.alanpoi.analysis.excel.exports.ReflectorManager;
import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.common.util.Placeholder;
import com.alanpoi.common.util.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
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
        return exportData(workbook, data, c, null);
    }

    public Workbook exportData(Workbook workbook, Collection<?> data, Class<?> c, List<String> specifyCol) {
        try {
            loadSheet(workbook, data, c, 0, specifyCol);
        } catch (Exception e) {
            logger.error("", e);
            throw new AlanPoiException(e.getMessage());
        }
        return workbook;
    }

    public Workbook exportMultipleSheet(Workbook workbook, Map<Class<?>, Collection<?>> dataMap) {
        try {
            int sheetAt = 0;
            for (Class<?> c : dataMap.keySet()) {
                Collection<?> collection = dataMap.get(c);
                loadSheet(workbook, collection, c, sheetAt, null);
                sheetAt++;
            }
        } catch (Exception e) {
            logger.error("", e);
            throw new AlanPoiException(e.getMessage());
        }
        return workbook;
    }

    private void loadSheet(Workbook workbook, Collection<?> data, Class<?> c, final int sheetAt, List<String> specifyCol) {
        CellStyle headStyle = workbook.createCellStyle();
        headStyle.setWrapText(true);
        ReflectorManager reflectorManager = ReflectorManager.fromCache(c);
        ExcelSheet excelSheet = c.getAnnotation(ExcelSheet.class);
        int rowInd = 0;
        Sheet sheet = null;
        Row headRow = null;
        if (excelSheet != null) {

            sheet = workbook.getSheetAt(excelSheet.index());
            workbook.setSheetName(excelSheet.index(), excelSheet.name());

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
        int cellNum = 0;
        Set<Integer> disableNum = new HashSet<>();
        List<ExcelParseParam> excelParseParamList = new ArrayList<>();
        for (int i = 0; i < fieldLength; i++) {
            Field field = fields.get(i);
            ExcelColumn excelColumn = fields.get(i).getAnnotation(ExcelColumn.class);
            DateFormat dateFormat = fields.get(i).getAnnotation(DateFormat.class);
            NumFormat numFormat = fields.get(i).getAnnotation(NumFormat.class);
            ExcelParseParam excelParseParam = new ExcelParseParam();
            if (excelColumn != null) {
                Integer index = null;
                if (StringUtils.isNotBlank(excelColumn.index()))
                    index = Integer.valueOf(excelColumn.index());
                if (!excelColumn.isExist()) continue;
                if (!CollectionUtils.isEmpty(specifyCol)) {
                    if (!specifyCol.contains(field.getName())) {
                        if (StringUtils.isNotBlank(excelColumn.index()))
                            disableNum.add(Integer.valueOf(excelColumn.index()));
                        continue;
                    } else {
                        index = specifyCol.indexOf(field.getName());
                    }
                }
                Cell cell;
                if (null != index) {
                    cell = headRow.createCell(index);
                    excelParseParam.setIndex(index);
                    sheet.setColumnWidth(index, excelColumn.width() * 256);
                } else {
                    cell = headRow.createCell(cellNum);
                    excelParseParam.setIndex(cellNum);
                    sheet.setColumnWidth(cellNum, excelColumn.width() * 256);
                }
                cell.setCellValue(excelColumn.name());
                cell.setCellStyle(headStyle);
                String link = excelColumn.link();
                if (StringUtils.isNotBlank(link)) {
                    excelParseParam.setSourceLink(link);
                    if (link.indexOf(Placeholder.TYPE0.begin) != -1)
                        excelParseParam.setLinkMethod(reflectorManager.getGetMethod(StringUtils.findReplace(link, Placeholder.TYPE0)));
                }
                excelParseParam.setHeight(excelColumn.height());
                excelParseParam.setColor(excelColumn.color().index);
                excelParseParam.setCellStyle(workbook.createCellStyle());
            } else {
                Cell cell = headRow.createCell(cellNum);
                cell.setCellValue(field.getName());
                cell.setCellStyle(headStyle);
                excelParseParam.setIndex(cellNum);
            }
            if (dateFormat != null) {
                excelParseParam.setFormat(dateFormat.value());
            }
            if (numFormat != null) {
                excelParseParam.setNumFormat(numFormat.value());
            }
            excelParseParam.setMethod(reflectorManager.getGetMethod(field.getName()));
            excelParseParamList.add(excelParseParam);
            cellNum++;
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
        if (excelParseParams.get(0).getHeight() > 0)
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
                Workbook workbook = cell.getSheet().getWorkbook();
                Object value = excelParseParam.getMethod().invoke(object);
                if (StringUtils.isNotBlank(excelParseParam.getSourceLink())) {
                    //set hyperlink and style
                    Hyperlink hyperlink = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
                    String link = excelParseParam.getSourceLink();
                    if (null != excelParseParam.getLinkMethod())
                        link = (String) excelParseParam.getLinkMethod().invoke(object);
                    hyperlink.setAddress(StringUtils.replace(excelParseParam.getSourceLink(), link, Placeholder.TYPE0));
                    cell.setHyperlink(hyperlink);
                    Font font = workbook.createFont();
                    font.setUnderline((byte) 1);
                    font.setColor(AlanColor.BLUE.index);
                    excelParseParam.getCellStyle().setFont(font);
                }
                boolean isFormat = false;
                if (StringUtils.isNotBlank(excelParseParam.getFormat())) {
                    value = this.dateFormatValue(value, excelParseParam);
                    isFormat = true;
                } else if (StringUtils.isNotBlank(excelParseParam.getNumFormat())) {
                    value = this.numFormatValue(value, excelParseParam);
                    isFormat = true;
                }
                if (!isFormat) {
                    if (value == null) cell.setCellValue("");
                    else if (NumberUtils.isCreatable(value.toString())) {
                        if (value.toString().indexOf(".") != -1)
                            cell.setCellValue(Double.valueOf(value.toString()));
                        else
                            cell.setCellValue(Long.valueOf(value.toString()));
                    } else
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
