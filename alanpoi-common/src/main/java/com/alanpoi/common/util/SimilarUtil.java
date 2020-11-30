package com.alanpoi.common.util;

import com.alanpoi.common.algorithm.HtmlSimilar;
import com.alanpoi.common.algorithm.Similar;
import com.alanpoi.common.enums.SimilarPrecision;

import java.io.IOException;
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

    /**
     * Compare the similarity of the two web pages
     *
     * @param url1
     * @param url2
     * @return
     * @throws IOException
     */
    public static double compare(String url1, String url2) throws IOException {
        return HtmlSimilar.getInstance().compare(url1, url2);
    }
}
