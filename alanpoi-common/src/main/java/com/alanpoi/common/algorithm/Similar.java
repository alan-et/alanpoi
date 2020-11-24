package com.alanpoi.common.algorithm;

import com.alanpoi.common.config.WordSegInitConfig;
import com.alanpoi.common.enums.SimilarPrecision;
import com.alanpoi.common.event.EventDispatcher;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Calculate the similarity between the two vectors
 *
 * @author pengzhuoxun
 * @since 1.3.2
 */
public class Similar {

    private static volatile Similar similar;

    /**
     * Get a single
     *
     * @return
     */
    public static Similar getInstance() {
        if (similar != null) {
            return similar;
        }
        synchronized (Similar.class) {
            if (null == similar) similar = new Similar();
            return similar;
        }

    }

    /**
     * Calculate the similarity of two arrays
     *
     * @param val1 vector
     * @param val2 vector
     * @return
     */
    public double calculate(List<String> val1, List<String> val2, SimilarPrecision precision) {
        if (CollectionUtils.isEmpty(val1) || CollectionUtils.isEmpty(val2))
            throw new IllegalArgumentException();

        Set<String> allData = new HashSet<>();
        allData.addAll(val1);
        allData.addAll(val2);
        int size = allData.size();
        //vector1 word frequency
        Integer[] arr1 = new Integer[size];
        //vector2 word frequency
        Integer[] arr2 = new Integer[size];
        int i = 0;
        for (String str : allData) {
            arr1[i] = (int) val1.stream().filter((d -> d.equals(str))).count();
            arr2[i++] = (int) val2.stream().filter(d -> d.equals(str)).count();
        }
        //calc
        long molecule_val = 0;
        double d_val1 = 0.0;
        double d_val2 = 0.0;
        for (int h = 0; h < size; h++) {
            int x = arr1[h];
            int y = arr2[h];
            molecule_val += x * y;
            d_val1 += Math.pow(x, 2);
            d_val2 += Math.pow(y, 2);
        }
        double calcVal = molecule_val / (Math.sqrt(d_val1) * Math.sqrt(d_val2));
        return Double.valueOf(String.format(precision.val, calcVal));
    }

    /**
     * Calculate the similarity of two texts
     *
     * @param text1 text1
     * @param text2 text2
     * @return
     */
    public double calculate(String text1, String text2, SimilarPrecision precision) {
        //It is recommended to start initialization WordSegInitConfig
        List<String> list1 = WordSegInitConfig.segToString(text1, SegmentationAlgorithm.FullSegmentation);
        List<String> list2 = WordSegInitConfig.segToString(text2, SegmentationAlgorithm.FullSegmentation);
        return new Similar().calculate(list1, list2, precision);
    }
}
