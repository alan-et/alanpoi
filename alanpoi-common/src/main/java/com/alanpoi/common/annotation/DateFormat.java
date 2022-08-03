package com.alanpoi.common.annotation;

import java.lang.annotation.*;

/**
 * DateFormat annotation
 *
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
@Documented
public @interface DateFormat {
    String value() default "yyyy/MM/dd";
}
