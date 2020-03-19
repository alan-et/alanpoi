package com.alanpoi.excel.annotation;

import com.alanpoi.common.AlanColor;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel sheet annotation
 *
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ExcelSheet {
    String name() default "";

    int index() default 0;

    int rowHeight() default 30;

    String font() default "黑体";

    int fontSize() default 20;

    AlanColor backColor() default AlanColor.AQUA;
}
