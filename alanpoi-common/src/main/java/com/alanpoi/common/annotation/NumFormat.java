package com.alanpoi.common.annotation;

import java.lang.annotation.*;

/**
 * NumFormat annotation
 *
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
@Documented
public @interface NumFormat {
    String value() default "00.00";
}
