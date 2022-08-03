
/**
 * @author brandon
 * @create 2019-10-22
 */

package com.alanpoi.common.config;

import com.alanpoi.common.util.StringUtils;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.util.WordConfTools;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 使用word分词器实现的文本过滤器
 *
 * @author pengzhuoxun
 * @since 1.3.3
 */
public class WordSegInitConfig {

    private String dicPath;

    public WordSegInitConfig() {
        init(null);
    }

    public WordSegInitConfig(String dicPath) {
        this.dicPath = dicPath;
        init(dicPath);
    }

    public static void init() {
        init(null);
    }

    public static void init(@Nullable String dicPath) {
        if (StringUtils.isNotBlank(dicPath)) {
            //设置自定义词典
            String dictPath = "classpath:dic.txt" + "," + dicPath;
            //set()会自动加载配置
            WordConfTools.set("dic.path", dictPath);
            //WordConfTools.reload();这句demo会导致上面添加的自定义词库失效
            //DictionaryFactory.reload();
        }
        WordSegmenter.seg("预加载");
    }


    private static List<String> wordsToStringList(Collection<Word> words) {
        List<String> res = new ArrayList<>(words.size());
        for (Word w : words) {
            res.add(w.getText());
        }
        return res;
    }

    public static List<String> segToString(String text) {
        return wordsToStringList(WordSegmenter.seg(text));
    }

    public static List<String> segToString(String text, SegmentationAlgorithm type) {
        return wordsToStringList(WordSegmenter.seg(text, type));
    }

    public static List<String> segByAllToString(String text, SegmentationAlgorithm type) {
        return wordsToStringList(WordSegmenter.segWithStopWords(text, type));
    }

    public static List<Word> seg(String text) {
        return WordSegmenter.seg(text);
    }

    public static List<Word> seg(String text, SegmentationAlgorithm type) {
        return WordSegmenter.seg(text, type);
    }

    public static List<Word> segByAll(String text) {
        return WordSegmenter.segWithStopWords(text);
    }

    public static List<Word> segByAll(String text, SegmentationAlgorithm type) {
        return WordSegmenter.segWithStopWords(text, type);
    }
}
