## 前言

针对Excel操作，alanpoi是为了实现一个操作更加简单，开发效率更加高的工具，开发者不需要关系太多的逻辑，只需要处理和自己业务相关的逻辑

## 功能介绍


### ExcelHandle  核心处理器

### ExcelWorkbookManage  excel所有工作表管理

### ExcelHelper  配置文件初始化

### AbstractFileParser  文件转换类

## alanpoi有何优势？

1. 用户不需要额外引入poi等繁琐的jar
2. 毫秒级解析大文件，支持一键解析多sheet页签，不需要自己按照一定的格式循环匹配解析所有数据
3. 不管你的系统多么复杂，有多少个导入，alanpoi全部支持，而且准确返回你需要的对象，减轻开发者工作量
4. 目前外界业务越来越复杂，对各个功能要求也越来越严格，当然导入也不例外，alanpoi支持错误一键会写到excel，对应到每一行
5. alanpoi灵活可扩展，提供了ExcelConsumeInterface接口，可继承valid、error、end三个方法实现自己的业务 </br>
     A. valid: 方法参数反馈excel所有数据，用户可进行自我校验</br>
     B. error: 导入错误会回调</br>
     C. end: 方法参数返回校验成功的数据，valid校验失败的数据不会返回，用户可以自己操作持久化或者其他业务操作

## 怎么使用alanpoi

简单一句话:一配置一继承一调用

### 一配置

在项目resources目录中新建excel-config.xml文件,cosume中配置自己的消费类路径，继承提供了ExcelConsumeInterface接口，sheet中的vo是把当切sheet序列化的对象路径，column中当然就是配置vo中的属性了，导入包含多个sheet就配置多个

<?xml version = "1.0" encoding = "GB2312"?>
<exg name="excelId" version="1.0" file-type="excel">
    <excel id="EXCEL_ID" consume="">
        <sheet index="0" row-start="2" column-start="0" vo="">
             <column offset="1">id</column>
        </sheet>
   </excel>
</exg>

### 一继承

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
    void validData(String workbookId, List<ExcelSheetData> sheetDataList);

    /**
     *
     * @param sheetDataList return success data
     */
    void end(List<ExcelSheetData> sheetDataList);

### 一调用

用户调用ExcelParser类的importData即可，参数excelId就是excel-conifg.xml中配置的id






