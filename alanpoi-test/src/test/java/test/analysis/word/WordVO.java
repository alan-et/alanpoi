package test.analysis.word;

import com.alanpoi.analysis.common.enums.WordAlign;
import com.alanpoi.analysis.common.enums.WordHighlight;
import com.alanpoi.analysis.common.enums.WordStyle;
import com.alanpoi.analysis.word.annotation.WordDefine;
import com.alanpoi.analysis.word.annotation.WordField;
import lombok.Data;

@WordDefine
@Data
public class WordVO {

    @WordField(pStyle = WordStyle.TITLE1,align = WordAlign.center)
    private String title;

    @WordField
    private String name;

    @WordField(name = "正文", highlight = WordHighlight.CYAN)
    private String content;
}
