package com.alanpoi.common.util;

import com.alanpoi.common.algorithm.Similar;
import com.alanpoi.common.enums.SimilarPrecision;

import java.util.List;

public class SimilarUtil {

    /**
     * Calculate the similarity of two arrays
     *
     * @param val1 vector
     * @param val2 vector
     * @return
     */
    public static double calculate(List<String> val1, List<String> val2) {
        return calculate(val1, val2, SimilarPrecision.THREE_DECIMAL);
    }

    /**
     * Calculate the similarity of two arrays
     *
     * @param val1      vector
     * @param val2      vector
     * @param precision
     * @return
     */
    public static double calculate(List<String> val1, List<String> val2, SimilarPrecision precision) {
        return Similar.getInstance().calculate(val1, val2, precision);
    }

    /**
     * Calculate the similarity of two texts
     *
     * @param text1 text1
     * @param text2 text2
     * @return
     */
    public static double calculate(String text1, String text2) {
        return calculate(text1, text2, SimilarPrecision.THREE_DECIMAL);
    }

    /**
     * Calculate the similarity of two texts
     *
     * @param text1
     * @param text2
     * @param precision
     * @return
     */
    public static double calculate(String text1, String text2, SimilarPrecision precision) {
        return Similar.getInstance().calculate(text1, text2, precision);
    }
}
