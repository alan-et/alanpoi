package com.alanpoi.analysis.excel.annotation;

import com.alanpoi.analysis.common.enums.AlanColor;
import com.alanpoi.analysis.common.enums.Align;
import com.alanpoi.analysis.common.enums.DataType;
import com.alanpoi.analysis.common.enums.VertAlign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel Column annotation
 *
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelColumn {
    String name() default "";

    String index() default "";

    boolean isExist() default true;

    int height() default 20;

    int width() default 30;

    /**
     * 左右布局
     *
     * @return
     */
    Align align() default Align.LEFT;

    /**
     * 自动换行
     *
     * @return
     */
    boolean wrapText() default true;

    /**
     * 垂直布局
     *
     * @return
     */
    VertAlign vertical() default VertAlign.CENTER;

    String link() default "";

    DataType type() default DataType.TEXT;

    AlanColor color() default AlanColor.NONE;

    /**
     * 自动合并
     *
     * @return
     * @since 1.3.7
     */
    boolean autoMerge() default false;
}
