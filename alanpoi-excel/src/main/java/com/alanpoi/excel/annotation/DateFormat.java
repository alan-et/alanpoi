package com.alanpoi.excel.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * DateFormat annotation
 *
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@com.alanpoi.common.annotation.DateFormat
@Deprecated
public @interface DateFormat {

    @AliasFor(annotation = com.alanpoi.common.annotation.DateFormat.class)
    String value() default "yyyy/MM/dd";
}
