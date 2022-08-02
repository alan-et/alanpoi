# 前言

针对Excel、word、pdf操作，alanpoi是为了实现一个操作更加简单，开发效率更加高的工具，开发者不需要关心太多的逻辑，只需要处理和自己业务相关的部分;
化繁为简，由简变精的原则

项目中使用:

```
  <dependency>
      <groupId>com.alanpoi</groupId>
      <artifactId>alanpoi-analysis</artifactId>
      <version>1.3.4</version>
  </dependency>
```

# Excel

## Api document

通过 [Api doc](https://alanpoi.com:8162/index.html) 更加深入了解它！

## 一. IMPORT

1. ExcelHandle 核心处理器<br>

2. ExcelWorkbookManage excel所有工作表管理<br>

3. ExcelInitConfig 配置文件初始化<br>

4. AbstractFileParser 文件转换类<br>

### alanpoi import有何优势？

1. 用户不需要额外引入poi等繁琐的jar
2. 毫秒级解析大文件，支持一键解析多sheet页签，省去了繁琐的解析逻辑
3. 不管你的系统多么复杂，有多少个导入，alanpoi全部支持，而且准确返回你需要的对象，减轻开发工作量
4. 目前外界业务越来越复杂，对各个功能要求也越来越严格，当然导入也不例外，alanpoi支持错误一键回写到excel
5. alanpoi灵活可扩展，提供了ExcelConsumeInterface接口，可继承它，实现valid、error、end三个方法编写自己的业务 </br>
   A. valid: 方法参数返回excel所有数据，用户可进行自我校验</br>
   B. error: 导入错误会回调</br>
   C. end: 方法参数返回校验成功的数据，valid校验失败的数据不会返回，用户可以自己操作持久化或者其他业务

### 怎么使用alanpoi实现导入

简单一句话:一配置一继承一调用

#### 一配置

在项目resources目录中新建excel-config.xml文件,cosume中配置自己的消费类路径，继承ExcelConsumeInterface接口，sheet中的vo是把当前sheet序列化的对象路径，column中当然就是配置vo中的属性了，
其中name可选字段，填了就是按照这个匹配excel列名，不填就是按照offset顺序；导入包含多个sheet就配置多个

```
   <?xml version = "1.0" encoding = "GB2312"?>
   <exg name="excelId" version="1.0" file-type="excel">
     <excel id="ACCOUNT" consume="com.xxx.FinAccountImportHandler">
        <sheet index="0" row-start="1" column-start="0"
               vo="com.xxx.vo.FinAccountImportVO">
            <column name="公司/供应商编号" offset="1">companyCode</column>
            <column name="公司/供应商名称" offset="2">companyName</column>
            <column name="银行账号" offset="3">bankAccount</column>
            <column name="开户银行" offset="4">bankName</column>
        </sheet>
    </excel>
   </exg>
```

#### 一继承

consume类继承ExcelConsumeInterface接口，实现方法

    /**
     * when error will 调用
     *
     * @param excelError
     */
    void error(ExcelError excelError);

    /**
     * custom valid data
     *
     * @param workbookId
     * @param sheetDataList
     */
    void validData(String workbookId, List<ExcelSheetData> sheetDataList, Map<Serializable, Object> excelParam);

    /**
     * @param sheetDataList return success data
     */
    void end(List<ExcelSheetData> sheetDataList, Map<Serializable, Object> excelParam);

#### 一调用

用户调用ExcelExportUtil类的customImportData即可，参数excelId就是excel-conifg.xml中配置的id

## Export

### 描叙

能够用一行代码实现绝不用第二行，如果一行不行，那就再加一行！

### 模式

#### 使用注解模式导出

ExcelSheet注解：用于导入类上，可制定sheet名，列头的颜色、字体、高度、宽度<br>
ExcelColumn注解: 用于导入类的属性上，可指定列头的名称，单元格的样式<br>
DateFormat注解: 用于导入类的属性上, 可以按照指定格式输出到excel,默认"yyyy/MM/dd"
NumFormat注解: 用于导入类的属性上，可以按照指定格式输出到excel,默认"00.00"

样例：

```
@ExcelSheet(name = "测试", backColor = AlanColors.GREEN, font = "宋体", fontSize = 25)
@Data
public class ExportVO {
    @ExcelColumn(name = "名称", width = 32, link = "${url}")
    private String name;
    
    @ExcelColumn(name = "图片", index = "5", width = 50, height = 100, type = DataType.IMAGE)
    private String image;

    @ExcelColumn(name = "值",autoMerge = true, align = Align.CENTER)
    private String value;

    @ExcelColumn(name = "金额")
    @NumFormat(value = "0000.00##")
    private BigDecimal amount;

    @ExcelColumn(name = "时间格式化")
    @DateFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;
    
    @ExcelColumn(name = "图片", index = "5", width = 50, height = 100, type = DataType.IMAGE)
    private String image;

    @ExcelColumn(name = "图片2", index = "6", width = 50, height = 100, type = DataType.IMAGE)
    private byte[] image2;

    @DateFormat
    @ExcelColumn(name = "日期格式化")
    private java.sql.Date date;
    
    @ExcelColumn(isExist = false)
    private String url;
}
```

#### 使用

方式一. 直接导出到浏览器<br>
ExcelExportUtil.export(Colletion<?>,Class,HttpServletRequest,HttpServletResponse,fileName);<br><br>
方式二. 调用getWorkbook获取工作表,自行处理workbook<br>
ExcelExportUtil.getWorkbook(Collection<?> singleSheetData, Class<?> c)<br>

#### 高级使用

*支持图片、超链接、合并单元格等复杂场景*

示例一：导出指定列（动态导出列）<br>
**使用场景: 导出的列是不固定的时，比如用户可以手动勾选的导出列**

```
    List<ExportVO> list = new ArrayList<>();
    for (int i = 0; i < 500; i++) {
        ExportVO exportVO = new ExportVO();
        exportVO.setName("name" + i);
        exportVO.setValue(new BigDecimal(123.11 + i * 0.09));
        exportVO.setAmount(new BigDecimal(6666.666 + i * 10));
        exportVO.setDate(new Date(132324343 + i * 100));
        exportVO.setDateTime(new java.util.Date());
        list.add(exportVO);
    }
    List<String> colList = new ArrayList<>();
    //按照顺序仅导出add的列
    colList.add("name");
    colList.add("value");
    //调用获取workbook对象；也可以直接调用exportSpecifyCol方法导出到浏览器
    Workbook workbook = ExcelExportUtil.getWorkbookSpecifyCol(list, ExportVO.class, colList);
```

示例二：多sheet页签导出
**使用场景: 用户需要导出多个不同业务的数据，可用不同sheet页签呈现**

```
    List<ExportVO> list = new ArrayList<>();
    List<Export2VO> list2 = new ArrayList<>();
    for (int i = 0; i < 500; i++) {
        ExportVO exportVO = new ExportVO();
        exportVO.setName("name" + i);
        exportVO.setValue(new BigDecimal(123.11 + i * 0.09));
        exportVO.setAmount(new BigDecimal(6666.666 + i * 10));
        exportVO.setDate(new Date(132324343 + i * 100));
        exportVO.setDateTime(new java.util.Date());
        list.add(exportVO);
        Export2VO export2VO = new Export2VO();
        export2VO.setName("name" + i);
        export2VO.setValue("value" + i);
        export2VO.setAmount(new BigDecimal(6666.666 + i * 10));
        export2VO.setDate(new Date(132324343 + i * 100));
        export2VO.setDateTime(new java.util.Date());
        export2VO.setImage("https://t7.baidu.com/it/u=1569919947,316169633&fm=218&app=92&f=PNG?w=121&h=75&s=6ED4EC1A07384D8A0654DCDE03007031");
        export2VO.setImage2(byteArrayOut.toByteArray());
        list2.add(export2VO);
    }
    Map<Class<?>, Collection<?>> map = new HashMap<>();
    map.put(ExportVO.class, list);
    map.put(Export2VO.class, list2);
    //调用获取workbook对象；也可以直接调用exportByMultiSheet方法导出到浏览器
    Workbook workbook = ExcelExportUtil.getWorkbookByMultiSheet(map);
```

# Word

### 1. 注解模式导出doc

```java

@WordDefine
@Data
public class WordVO {

    @WordField(pStyle = WordStyle.TITLE1, align = WordAlign.center)
    private String title;

    @WordField
    private String name;

    @WordField(name = "正文", highlight = WordHighlight.CYAN)
    private String content;
}
```

```java
public static void main(String[]args){
        WordVO wordVO=new WordVO();
        wordVO.setTitle("Alanpoi");
        wordVO.setName("名称");
        wordVO.setContent("样例内容");
        wordHandle.setWord2003();
        IWordWorkbook workbook=wordHandle.getWorkbook(WordVO.class,wordVO);
        workbook.write(new FileOutputStream("/data/test.doc"));
        }
```

### 2. 使用模版导出doc、docx <br>

***推荐使用***

#### 优点

1. 此方式生成时性能是毫秒级，经过测试即使上百页word，生成耗时不超过100毫秒
2. 使用灵活，结合freemarker引擎一套模版可适应多种场景

#### 不足

1. 针对docx，对于使用者来说，不知道怎么使用模版
2. 大多使用者对微软word的open xml语法不熟
   <br>（针对上述问题，后续我会开发一套自动生成模版的系统，目前如果不知道怎么制作模版，可以私信我帮忙处理）

#### 使用

```java
/**
 * 注意导出docx，其中的图片数据（base64）单独存放，通过id关联，而生成doc图片数据直接以base64传输进去即可
 */
//导出docx
IWordWorkbook workbook=wordHandle.getWorkbook2007("templates/docx.tpl",param);
        workbook.write(new FileOutputStream("/data/test.docx"));
        workbook.close();

//导出docx到浏览器（直接把文件写入到response）
        wordHandle.export2007("/temp/temp.tpl",param，mediaList,request,response);

//导出doc
        IWordWorkbook workbook=wordHandle.getWorkbook2003("templates/doc.ftl",param);
        workbook.write(new FileOutputStream("/data/test.doc"));
        workbook.close();

//导出doc到浏览器（直接把文件写入到response）
        wordHandle.export2003("/temp/temp.ftl",param,request,response);
```

# Pdf

#### 使用

```java
Map<String, Object> param=new HashMap<>();
        PdfConvert pdfConvert=PdfConvert.getInstance("templates/pdf.tpl",param);
        pdfConvert.createPdf(new FileOutputStream("/data/test.pdf"));
```


