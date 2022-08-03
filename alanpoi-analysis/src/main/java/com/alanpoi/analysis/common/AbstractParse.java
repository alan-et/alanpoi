package com.alanpoi.analysis.common;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

/**
 * @author pengzhuoxun
 * @since 1.3.4
 */
public abstract class AbstractParse {

    protected Configuration configure = null;


    public Template createTemplate(String parentPath, String fileName, boolean isAbsolute) throws IOException {
        //加载模板文件
        if (isAbsolute) {
            configure.setDirectoryForTemplateLoading(new File(parentPath));//模板文件在本地硬盘d
        } else {
            configure.setClassForTemplateLoading(getClass(), parentPath); //将模板文件直接复制到src目录下
        }

        //设置对象包装器
        configure.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        //设置异常处理器
        configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        //定义Template对象,注意模板类型名字与downloadType要一致
        Template template = configure.getTemplate(fileName);  //文件名调用的时候可更换
        template.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        template.setDateFormat("yyyy-MM-dd");
        template.setTimeFormat("HH:mm:ss");
        return template;
    }
}
