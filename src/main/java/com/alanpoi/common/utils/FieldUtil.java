package com.alanpoi.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * field util
 *
 * @author zhuoxun.peng
 * @since 2020-3-17
 */
public class FieldUtil {

    public static Field[] getClassFields(Class<?> clazz) {
        ArrayList list = new ArrayList();

        Field[] fields;
        do {
            fields = clazz.getDeclaredFields();

            for (int i = 0; i < fields.length; ++i) {
                list.add(fields[i]);
            }

            clazz = clazz.getSuperclass();
        } while (clazz != Object.class && clazz != null);

        return (Field[]) list.toArray(fields);
    }

}
