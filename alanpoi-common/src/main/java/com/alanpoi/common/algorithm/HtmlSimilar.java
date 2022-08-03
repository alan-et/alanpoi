package com.alanpoi.common.algorithm;

import com.alanpoi.common.enums.SimilarPrecision;
import com.alanpoi.common.util.HttpUtils;

import java.io.IOException;

/**
 * HTML Similar
 *
 * @author pengzhuoxun
 * @since 1.3.3
 */
public class HtmlSimilar extends Similar {
    protected static volatile HtmlSimilar htmlSimilar;

    /**
     * Get a single
     *
     * @return
     */
    public static HtmlSimilar getInstance() {
        if (htmlSimilar != null) {
            return htmlSimilar;
        }
        synchronized (HtmlSimilar.class) {
            if (null == htmlSimilar) htmlSimilar = new HtmlSimilar();
            return htmlSimilar;
        }
    }

    /**
     * Compare the similarity of the two web pages
     *
     * @param url1
     * @param url2
     * @return
     * @throws IOException
     */
    public double compare(String url1, String url2) throws IOException {
        String text1 = HttpUtils.getBodyText(url1);
        String text2 = HttpUtils.getBodyText(url2);
        return calculate(text1, text2, SimilarPrecision.THREE_DECIMAL);
    }
}
