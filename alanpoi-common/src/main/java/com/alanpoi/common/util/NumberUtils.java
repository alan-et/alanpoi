package com.alanpoi.common.util;

import org.apache.commons.lang3.SystemUtils;

public class NumberUtils {
    public static boolean isNumber(String str) {
        if (StringUtils.isBlank(str) || str.split("\\.")[0].length() > 19) {
            return false;
        }
        return isCreatable(str);
    }

    public static boolean isCreatable(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        } else {
            char[] chars = str.toCharArray();
            int sz = chars.length;
            boolean hasExp = false;
            boolean hasDecPoint = false;
            boolean allowSigns = false;
            boolean foundDigit = false;
            int start = chars[0] != '-' && chars[0] != '+' ? 0 : 1;
            boolean hasLeadingPlusSign = start == 1 && chars[0] == '+';
            int i;
            if (sz > start + 1 && chars[start] == '0') {
                if (chars[start + 1] == 'x' || chars[start + 1] == 'X') {
                    i = start + 2;
                    if (i == sz) {
                        return false;
                    }

                    while (i < chars.length) {
                        if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F')) {
                            return false;
                        }

                        ++i;
                    }

                    return true;
                }

                if (Character.isDigit(chars[start + 1])) {
                    for (i = start + 1; i < chars.length; ++i) {
                        if (chars[i] < '0' || chars[i] > '7') {
                            return false;
                        }
                    }

                    return true;
                }
            }

            --sz;

            for (i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                if (chars[i] >= '0' && chars[i] <= '9') {
                    foundDigit = true;
                    allowSigns = false;
                } else if (chars[i] == '.') {
                    if (hasDecPoint || hasExp) {
                        return false;
                    }

                    hasDecPoint = true;
                } else if (chars[i] != 'e' && chars[i] != 'E') {
                    if (chars[i] != '+' && chars[i] != '-') {
                        return false;
                    }

                    if (!allowSigns) {
                        return false;
                    }

                    allowSigns = false;
                    foundDigit = false;
                } else {
                    if (hasExp) {
                        return false;
                    }

                    if (!foundDigit) {
                        return false;
                    }

                    hasExp = true;
                    allowSigns = true;
                }
            }

            if (i < chars.length) {
                if (chars[i] >= '0' && chars[i] <= '9') {
                    return !SystemUtils.IS_JAVA_1_6 || !hasLeadingPlusSign || hasDecPoint;
                } else if (chars[i] != 'e' && chars[i] != 'E') {
                    if (chars[i] == '.') {
                        return !hasDecPoint && !hasExp ? foundDigit : false;
                    } else if (allowSigns || chars[i] != 'd' && chars[i] != 'D' && chars[i] != 'f' && chars[i] != 'F') {
                        if (chars[i] != 'l' && chars[i] != 'L') {
                            return false;
                        } else {
                            return foundDigit && !hasExp && !hasDecPoint;
                        }
                    } else {
                        return foundDigit;
                    }
                } else {
                    return false;
                }
            } else {
                return !allowSigns && foundDigit;
            }
        }
    }

    public static boolean isInteger(String str) {
        if (isNumber(str)) {
            return !str.contains(".");
        }
        return false;
    }
}
