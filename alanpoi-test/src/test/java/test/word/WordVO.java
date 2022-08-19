package test.word;


import com.alanpoi.word.annotation.WordDefine;
import com.alanpoi.word.annotation.WordField;
import com.alanpoi.word.enums.WordAlign;
import com.alanpoi.word.enums.WordHighlight;
import com.alanpoi.word.enums.WordStyle;
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
