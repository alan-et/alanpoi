package com.alanpoi.analysis.word.annotation;


import com.alanpoi.analysis.common.enums.WordAlign;
import com.alanpoi.analysis.common.enums.WordHighlight;
import com.alanpoi.analysis.common.enums.WordStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Word Field annotation
 *
 * @author zhuoxun.peng
 * @since 1.3.4
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface WordField {
    String name() default "";

    String index() default "";

    String color() default "";

    WordAlign align() default WordAlign.none;

    WordHighlight highlight() default WordHighlight.NONE;

    WordStyle pStyle() default WordStyle.NONE;

    WordStyle rStyle() default WordStyle.NONE;

    boolean isExist() default true;

    int size() default -1;

    String link() default "";

}
