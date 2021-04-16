package com.alanpoi.analysis.word.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Word Define annotation
 *
 * @author zhuoxun.peng
 * @since 1.3.4
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface WordDefine {
}
