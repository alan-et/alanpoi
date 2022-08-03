package com.alanpoi.analysis.excel.annotation;

import com.alanpoi.analysis.common.enums.AlanColor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel Image annotation
 *
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelImage {
    String name() default "";

    String index() default "";

    boolean isExist() default true;

    int height() default 20;

    int width() default 30;

    String link() default "";

    AlanColor color() default AlanColor.NONE;
}
