package com.alanpoi.analysis.excel.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * NumFormat annotation
 *
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Documented
@com.alanpoi.common.annotation.NumFormat
@Deprecated
public @interface NumFormat {

    @AliasFor(annotation = com.alanpoi.common.annotation.NumFormat.class)
    String value() default "00.00";
}
