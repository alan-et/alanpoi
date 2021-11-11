package com.alanpoi.analysis.excel.exports.handle;

import com.alanpoi.analysis.common.enums.AlanColor;
import com.alanpoi.analysis.common.enums.DataType;
import com.alanpoi.analysis.excel.annotation.ExcelColumn;
import com.alanpoi.analysis.excel.annotation.ExcelSheet;
import com.alanpoi.analysis.excel.exports.ExcelParseParam;
import com.alanpoi.analysis.common.ReflectorManager;
import com.alanpoi.common.annotation.DateFormat;
import com.alanpoi.common.annotation.NumFormat;
import com.alanpoi.common.exception.AlanPoiException;
import com.alanpoi.common.util.NumberUtils;
import com.alanpoi.common.util.Placeholder;
import com.alanpoi.common.util.StringUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExportHandle {
    protected static final Logger logger = LoggerFactory.getLogger(ExportHandle.class);

    public static boolean is2007 = true;

    public Workbook exportData(Workbook workbook, Collection<?> data, Class<?> c) {
        return exportData(workbook, data, c, null);
    }

    public Workbook exportData(Workbook workbook, Collection<?> data, Class<?> c, List<String> specifyCol) {
        try {
            getWorkVersion(workbook);
            loadSheet(workbook, data, c, 0, specifyCol);
        } catch (Exception e) {
            logger.error("", e);
            throw new AlanPoiException(e.getMessage());
        }
        return workbook;
    }

    public Workbook exportMultipleSheet(Workbook workbook, Map<Class<?>, Collection<?>> dataMap) {
        return exportMultipleSheet(workbook, dataMap, new HashMap<>());
    }

    public Workbook exportMultipleSheet(Workbook workbook, Map<Class<?>, Collection<?>> dataMap, Map<Integer, List<String>> specifyCol) {
        try {
            getWorkVersion(workbook);
            int sheetAt = 0;
            for (Class<?> c : dataMap.keySet()) {
                Collection<?> collection = dataMap.get(c);
                ExcelSheet excelSheet = c.getAnnotation(ExcelSheet.class);
                if (excelSheet != null)
                    loadSheet(workbook, collection, c, sheetAt, specifyCol.get(excelSheet.index()));
                else
                    loadSheet(workbook, collection, c, sheetAt, specifyCol.get(sheetAt));
                sheetAt++;
            }
        } catch (Exception e) {
            logger.error("", e);
            throw new AlanPoiException(e.getMessage());
        }
        return workbook;
    }

    private void getWorkVersion(Workbook workbook) {
        if (workbook instanceof XSSFWorkbook) {
            is2007 = true;
        } else {
            is2007 = false;
        }
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
            /**
             * {@link com.alanpoi.analysis.excel.annotation.NumFormat
             * @link com.alanpoi.analysis.excel.annotation.DateFormat}
             * The above two annotations are planned to be removed in v2.0
             */
            ExcelColumn excelColumn = AnnotatedElementUtils.findMergedAnnotation(fields.get(i), ExcelColumn.class);
            DateFormat dateFormat = AnnotatedElementUtils.findMergedAnnotation(fields.get(i), DateFormat.class);
            NumFormat numFormat = AnnotatedElementUtils.findMergedAnnotation(fields.get(i), NumFormat.class);
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
                    if (link.indexOf("${") != -1)
                        excelParseParam.setLinkMethod(reflectorManager.getGetMethod(StringUtils.findReplace(link, Placeholder.TYPE0)));
                }
                CellStyle style = workbook.createCellStyle();
                style.setAlignment(excelColumn.align().val);
                style.setVerticalAlignment(excelColumn.vertical().val);
                style.setWrapText(excelColumn.wrapText());
                excelParseParam.setDataType(excelColumn.type());
                excelParseParam.setHeight(excelColumn.height());
                excelParseParam.setColor(excelColumn.color().index);
                excelParseParam.setCellStyle(style);
            } else {
                Cell cell = headRow.createCell(cellNum);
                cell.setCellValue(field.getName());
                cell.setCellStyle(headStyle);
                excelParseParam.setIndex(cellNum);
                excelParseParam.setDataType(DataType.TEXT);
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
                Sheet sheet = cell.getSheet();
                Workbook workbook = sheet.getWorkbook();
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
                if (excelParseParam.getDataType() == DataType.IMAGE) {
                    if (drawingImage(workbook, sheet, cell, value)) {
                        return;
                    }
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
                    else if (NumberUtils.isNumber(value.toString())) {
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

    private boolean drawingImage(Workbook workbook, Sheet sheet, Cell cell, Object value) {
        try {
            Drawing drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor;
            if (is2007) {
                anchor = new XSSFClientAnchor(
                        0,
                        0,
                        0,
                        0,
                        cell.getColumnIndex(),
                        cell.getRowIndex(),
                        cell.getColumnIndex() + 1,
                        cell.getRowIndex() + 1
                );
            } else {
                anchor = new HSSFClientAnchor(
                        0,
                        0,
                        0,
                        0,
                        (short) cell.getColumnIndex(),
                        cell.getRowIndex(),
                        (short) (cell.getColumnIndex() + 1),
                        cell.getRowIndex() + 1
                );
            }
            anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_DONT_RESIZE);
            if (value instanceof byte[]) {
                drawing.createPicture(anchor, workbook.addPicture((byte[]) value, Workbook.PICTURE_TYPE_PNG));
                return true;
            } else if (value instanceof String) {
                String img = (String) value;
                BufferedImage bufferImg;
                ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                if (img.startsWith("http://") || img.startsWith("https://")) {
                    URL url = new URL(img);
                    bufferImg = ImageIO.read(url);
                } else {
                    bufferImg = ImageIO.read(new File(img));
                }
                int h = bufferImg.getHeight();
                int w = bufferImg.getWidth();
                double cellWidth = sheet.getColumnWidthInPixels(cell.getColumnIndex());
                double cellHeight = cell.getRow().getHeightInPoints() / 72 * 96;
                ImageIO.write(bufferImg, "png", byteArrayOut);
                Picture picture = drawing.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
                double h_r = h / cellHeight;
                double w_r = w / cellWidth;
                if (h_r <= 1.0 && w_r <= 1.0) {
                    picture.resize(w_r, h_r);
                } else {
                    if (h_r > w_r) {
                        picture.resize(1 / h_r * w_r, 1);
                    } else {
                        picture.resize(1, 1 / w_r * h_r);
                    }
                }

                return true;
            } else {
                logger.warn("单元格为图片时，值必须是路径或者图片字节类型[rowIndex({})]", cell.getRowIndex());
            }
        } catch (IOException ioException) {
            logger.warn("写入图片到单元格异常", ioException);
        } catch (Exception e) {
            logger.warn("写入图片到单元格异常", e);
        }
        return false;
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
            logger.warn("Data format error:" + value);
            return value;
        } else {
            Number d;
            if (value.toString().indexOf(".") == -1) {
                d = Long.parseLong(value.toString());
            } else {
                d = Double.parseDouble(value.toString());
            }
            DecimalFormat df = new DecimalFormat(parseParam.getNumFormat());
            return df.format(d);
        }
    }
}
