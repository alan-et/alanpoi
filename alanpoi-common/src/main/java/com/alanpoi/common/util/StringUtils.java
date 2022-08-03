package com.alanpoi.common.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 批量替换占位符¬
     *
     * @param source
     * @param param
     * @param placeholder
     * @return
     */
    public static String replace(String source, Map<String, String> param, Placeholder placeholder) {
        // 正则匹配
        Pattern regex = Pattern.compile(placeholder.regex);
        // 占位符可能匹配的次数
        int count = source.split(placeholder.begin).length - 1;
        for (int i = 0; i < count; i++) {
            Matcher matcher = regex.matcher(source);
            boolean flag = matcher.find();
            if (!flag) {
                // 没有匹配到，结束
                break;
            }
            String key = matcher.group(0);
            String repVal = formatParam(key);
            if (param.containsKey(repVal)) {
                source = source.replace(key, param.get(repVal));
            }
        }
        return source;
    }

    public static String findReplace(String source, Placeholder placeholder) {
        Pattern regex = Pattern.compile(placeholder.regex);
        Matcher matcher = regex.matcher(source);
        boolean flag = matcher.find();
        if (!flag) {
            return null;
        }
        String key = matcher.group(0);
        return formatParam(key);
    }

    public static String replace(String source, String replaceVal, Placeholder placeholder) {
        Pattern regex = Pattern.compile(placeholder.regex);
        Matcher matcher = regex.matcher(source);
        boolean flag = matcher.find();
        if (!flag) {
            return null;
        }
        String key = matcher.group(0);
        return source.replace(key, replaceVal);
    }

    public static String formatParam(String paramCode) {
        return paramCode.replaceAll("\\$\\{", "").replaceAll("\\}", "");
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
