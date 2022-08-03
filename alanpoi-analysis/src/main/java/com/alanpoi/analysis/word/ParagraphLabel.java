package com.alanpoi.analysis.word;

import com.alanpoi.analysis.xml.Label;
import com.alanpoi.common.enums.LabelName;

public class ParagraphLabel extends Label {

    public ParagraphLabel() {
        this.name = LabelName.paragraph.value;
    }
}
