package com.alanpoi.excel.imports;

import com.alibaba.fastjson.JSONObject;
import com.alanpoi.excel.imports.handle.ExcelHandle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author zhuoxun.peng
 * @since 2020-2-25
 */
@Slf4j
public abstract class AbstractFileParser<T> extends ExcelHandle {
    protected ExcelInitConfig excelInitConfig;

    public AbstractFileParser() {

    }

    /**
     * 初始化解析类
     *
     * @param fileName
     */
    private Workbook initWorkbook(InputStream inputStream, String fileName) {
        int version = (fileName.endsWith(".xls") ? 2003 : 2007);
        try {
            if (version == 2003) {
                return new HSSFWorkbook(inputStream);
            } else {
                return new XSSFWorkbook(inputStream);
            }
        } catch (IOException ioe) {
            log.error("", ioe);
        }
        return null;
    }

    /**
     * 转化excel只适合只有一个页签
     *
     * @param excelId
     * @param fileName
     * @return
     */
    public List<T> importSingleData(String excelId, InputStream inputStream, String fileName) {
        Workbook wb = initWorkbook(inputStream, fileName);
        //取得 导入Excel文件的配置文件中的预设结果
        Excel excel = excelInitConfig.getExcelSheet(excelId);
        excel.setFileName(fileName);
        List<ExcelSheet> scList = excel.getExcelSheets();
        ExcelSheet sc = scList.get(0);
        //取得  预设文件中指定sheet页
        Sheet sheet = wb.getSheetAt(sc.getIndex());
        //取得  预设文件中指定sheet页
        List list = parse(sc, sheet);
        ExcelSheetData excelSheetData = new ExcelSheetData();
        excelSheetData.setData(list);
        excelSheetData.setIndex(0);
        excelSheetData.setRowStart(sc.getRowStart());
        excelSheetData.setSheetName(sheet.getSheetName());
        String workbookId = UUID.randomUUID().toString();
        excelWorkbookManage.addWorkbook(workbookId, wb);
        consumeHandle(workbookId, excel, Arrays.asList(excelSheetData));
        return list;
    }

    public List<ExcelSheetData> getData(String excelId, InputStream inputStream, String fileName) {
        List<ExcelSheetData> sheetDataList = new ArrayList<>();
        Workbook wb = initWorkbook(inputStream, fileName);
        Excel excel = excelInitConfig.getExcelSheet(excelId);

        excel.setFileName(fileName);
        List<ExcelSheet> scList = excel.getExcelSheets();
        scList.forEach(sc -> {
            //取得  预设文件中指定sheet页
            Sheet sheet = wb.getSheetAt(sc.getIndex());
            ExcelSheetData sheetData = new ExcelSheetData();
            sheetData.setIndex(sc.getIndex());
            sheetData.setRowStart(sc.getRowStart());
            sheetData.setSheetName(sheet.getSheetName());
            sheetData.setData(parse(sc, sheet));
            sheetDataList.add(sheetData);
        });
        return sheetDataList;
    }

    public void download(String fileId, HttpServletResponse response, HttpServletRequest request) {
        String errorStr = redisTemplate.opsForValue().get("product:import:" + fileId);
        if (StringUtils.isEmpty(errorStr)) {
            return;
        }
        ErrorFile errorFile = JSONObject.parseObject(errorStr, ErrorFile.class);
        String fileName = errorFile.getFileName();
        String filepath = errorFile.getFilePath();
        InputStream in = null;
        CloseableHttpClient httpClient = null;
        if (errorFile.getIpAddress().equals(ApplicationUtil.getInetAddress().getHostAddress())) {
            File f = new File(filepath + fileName);
            try {
                in = new FileInputStream(f);
                if (!f.exists()) {
                    response.sendError(404, "File not found!");
                    return;
                }
            } catch (IOException e) {
                log.error("", e);
            }
        } else {

            try {
                //文件不在档期服务器，获取指定服务器文件
                httpClient = HttpClientBuilder.create().build();
                HttpGet httpGet = new HttpGet("http://" + errorFile.getIpAddress() + ":" + port + request.getRequestURI());
                CloseableHttpResponse closeableHttpResponse = null;
                closeableHttpResponse = httpClient.execute(httpGet);
                HttpEntity responseEntity = closeableHttpResponse.getEntity();
                log.info("请求地址:{},响应状态：{}", null, closeableHttpResponse.getStatusLine());
                in = responseEntity.getContent();
            } catch (Exception e) {
                log.warn("request remote server error:{}", e);
                return;
            } finally {

            }
        }

        response.setContentType("application/force-download;charset=UTF-8");
        final String userAgent = request.getHeader("USER-AGENT");
        try {
            if (userAgent.contains("MSIE") || userAgent.contains("Edge")) {// IE浏览器
                fileName = URLEncoder.encode(fileName, "UTF8");
            } else if (userAgent.contains("Mozilla")) {// google,火狐浏览器
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
            }
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            return;
        }

        OutputStream out = null;
        try {
//            in = new FileInputStream(f);
            int len = 0;
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return;
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
                if (httpClient != null) httpClient.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private ExcelImportRes consumeHandle(String workbookId, Excel excel, List<ExcelSheetData> sheetDataList) {
        try {
            ExcelImportRes res = process(workbookId, sheetDataList, excel);
            return res;
        } catch (Exception e) {
            log.error("consumeHandle exception:", e);
            return null;
        }
    }


    /**
     * 解析多sheet页签
     *
     * @param excelId
     * @param inputStream
     * @param fileName
     * @return
     */
    public ExcelImportRes importData(String excelId, InputStream inputStream, String fileName) {
        return importData(excelId, inputStream, fileName, new HashMap<>());
    }

    public ExcelImportRes importData(String excelId, InputStream inputStream, String fileName, Map<Serializable, Object> excelParam) {
        List<ExcelSheetData> sheetDataList = new ArrayList<>();
        Workbook wb = initWorkbook(inputStream, fileName);
        Excel excel = excelInitConfig.getExcelSheet(excelId);

        excel.setFileName(fileName);
        excel.setCustomParam(excelParam);
        List<ExcelSheet> scList = excel.getExcelSheets();
        scList.forEach(sc -> {
            //取得  预设文件中指定sheet页
            Sheet sheet = wb.getSheetAt(sc.getIndex());
            ExcelSheetData sheetData = new ExcelSheetData();
            sheetData.setIndex(sc.getIndex());
            sheetData.setRowStart(sc.getRowStart());
            sheetData.setSheetName(sheet.getSheetName());
            sheetData.setData(parse(sc, sheet));
            sheetDataList.add(sheetData);
        });
        String workbookId = UUID.randomUUID().toString();
        excelWorkbookManage.addWorkbook(workbookId, wb);
        return consumeHandle(workbookId, excel, sheetDataList);
    }

    private List parse(ExcelSheet sc, Sheet sheet) {

        ArrayList list = new ArrayList();
        int rows = sheet.getPhysicalNumberOfRows();//取得所有行

        if (log.isDebugEnabled()) {
            log.debug("\trows=" + rows);
        }

        try {
            //rows 是从1开始 与java数组不同
            for (int j = sc.getRowStart(); j < rows; j++) {
                if (null == sheet.getRow(j).getCell((short) 0)) {
                    break;
                }
                String index = null;
                if (CellType.NUMERIC == sheet.getRow(j).getCell((short) 0).getCellType()) {
                    index = String.valueOf(sheet.getRow(j).getCell((short) 0).getNumericCellValue());
                } else if (CellType.STRING == sheet.getRow(j).getCell((short) 0).getCellType()) {
                    index = sheet.getRow(j).getCell((short) 0).getStringCellValue();
                }
                if (null == index || "".equals(index)) {
                    break;
                }
                /*
                 * 根据传入的参数（结构、一个row）, 构建对象
                 * 在使用可以添加参数来修改
                 */
                T view = getData(sc, sheet.getRow(j));
                list.add(view);
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
//            try {
//                if (wb != null) wb.close();
//            } catch (IOException e) {
//                log.error("", e);
//            }
        }
        return list;
    }

    //解析返回对象
    abstract protected T getData(ExcelSheet sheet, Row row) throws Exception;
}
