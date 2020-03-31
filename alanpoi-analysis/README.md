# 前言

针对Excel操作，alanpoi是为了实现一个操作更加简单，开发效率更加高的工具，开发者不需要关心太多的逻辑，只需要处理和自己业务相关的部分;<br>

化繁为简，由简变精


# 功能介绍

## 一. IMPORT

 1. ExcelHandle  核心处理器<br>

 2. ExcelWorkbookManage  excel所有工作表管理<br>

 3. ExcelInitConfig  配置文件初始化<br>

 4. AbstractFileParser  文件转换类<br>

### alanpoi import有何优势？

1. 用户不需要额外引入poi等繁琐的jar
2. 毫秒级解析大文件，支持一键解析多sheet页签，不需要自己按照一定的格式循环匹配解析所有数据
3. 不管你的系统多么复杂，有多少个导入，alanpoi全部支持，而且准确返回你需要的对象，减轻开发者工作量
4. 目前外界业务越来越复杂，对各个功能要求也越来越严格，当然导入也不例外，alanpoi支持错误一键回写到excel，对应到每一行
5. alanpoi灵活可扩展，提供了ExcelConsumeInterface接口，可继承它，实现valid、error、end三个方法编写自己的业务 </br>
     A. valid: 方法参数返回excel所有数据，用户可进行自我校验</br>
     B. error: 导入错误会回调</br>
     C. end: 方法参数返回校验成功的数据，valid校验失败的数据不会返回，用户可以自己操作持久化或者其他业务

### 怎么使用alanpoi实现导入

简单一句话:一配置一继承一调用

#### 一配置

在项目resources目录中新建excel-config.xml文件,cosume中配置自己的消费类路径，继承ExcelConsumeInterface接口，sheet中的vo是把当前sheet序列化的对象路径，column中当然就是配置vo中的属性了， 其中name可选字段，填了就是按照这个匹配excel列名，不填就是按照offset顺序；导入包含多个sheet就配置多个


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

#### 使用注解模式导入

ExcelSheet注解：用于导入类上，可制定sheet名，列头的颜色、字体、高度、宽度<br>
ExcelColum注解: 用于导入类的属性上，可指定列头的名称，单元格的样式<br>
DateFormat注解: 用于导入类的属性上, 可以按照指定格式输出到excel,默认"yyyy/MM/dd"
NumFormat注解: 用于导入类的属性上，可以按照指定格式输出到excel,默认"00.00"

样例：
```
@ExcelSheet(name = "测试", backColor = AlanColors.GREEN, font = "宋体", fontSize = 25)
@Data
public class ExportVO {
    @ExcelColumn(name = "名称", width = 32)
    private String name;

    @ExcelColumn(name = "值")
    private String value;

    @ExcelColumn(name = "金额")
    @NumFormat(value = "0000.00##")
    private BigDecimal amount;

    @ExcelColumn(name = "时间格式化")
    @DateFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date dateTime;

    @DateFormat
    @ExcelColumn(name = "日期格式化")
    private java.sql.Date date;
}
```
#### 使用
方式一. 直接导出到浏览器<br>
ExcelExportUtil.export(Colletion<?>,Class,HttpServletRequest,HttpServletResponse,fileName);<br><br>
方式二. 调用getWorkbook获取工作表,自行处理workbook<br>
ExcelExportUtil.getWorkbook(Collection<?> singleSheetData, Class<?> c)<br>







