package com.alanpoi.excel.parse;


import com.alanpoi.common.util.ReflectorManager;
import com.alanpoi.xml.AbstractParse;
import freemarker.template.TemplateException;
import org.jdom2.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

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
