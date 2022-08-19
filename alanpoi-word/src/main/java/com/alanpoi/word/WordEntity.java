package com.alanpoi.word;

import com.alanpoi.word.enums.WordAlign;
import com.alanpoi.word.enums.WordHighlight;
import com.alanpoi.word.enums.WordStyle;

public class WordEntity {
    private String name;

    private String value;

    private String index;

    private String color;

    private WordAlign align;

    private WordHighlight highlight;

    private WordStyle pStyle;

    private WordStyle rStyle;

    private int size;

    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public WordAlign getAlign() {
        return align;
    }

    public void setAlign(WordAlign align) {
        this.align = align;
    }

    public WordHighlight getHighlight() {
        return highlight;
    }

    public void setHighlight(WordHighlight highlight) {
        this.highlight = highlight;
    }

    public WordStyle getpStyle() {
        return pStyle;
    }

    public void setpStyle(WordStyle pStyle) {
        this.pStyle = pStyle;
    }

    public WordStyle getrStyle() {
        return rStyle;
    }

    public void setrStyle(WordStyle rStyle) {
        this.rStyle = rStyle;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
