package com.alanpoi.excel.parse;

import com.alanpoi.common.util.FileUtils;
import com.alanpoi.common.util.Placeholder;
import com.alanpoi.common.util.ReflectorManager;
import com.alanpoi.common.util.StringUtils;
import com.alanpoi.excel.exports.RowEntity;
import com.alanpoi.xml.AbstractParse;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据解析
 *
 * @author pengzhuoxun
 * @since 2.0.0
 */
public class ExcelDataParse extends AbstractParse {

    private static Logger log = LoggerFactory.getLogger(ExcelDataParse.class);

    private ZipPackage zipPackage;

    public ExcelDataParse(ZipPackage zipPackage) {
        this.zipPackage = zipPackage;
    }

    public void exec(List<?> dataList, Class<?> cls, int beginRow) throws IOException, TemplateException, JDOMException {
        ReflectorManager reflectorManager = ReflectorManager.fromCache(cls);
        ((ExcelZipPackage) zipPackage).writeShared(dataList, reflectorManager, beginRow);
        zipPackage.complete();
    }
}
