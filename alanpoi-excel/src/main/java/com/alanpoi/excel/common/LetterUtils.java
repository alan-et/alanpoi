package com.alanpoi.excel.common;

public class LetterUtils {
    private static String[] LETTER_MAP = new String[]{"A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    private static String getColLetter(int index, StringBuffer colLetter) {
        int num = index / 26;
        if (num < 1) {
            colLetter.append(LETTER_MAP[index]);
        }
        if (num >= 1) {
            colLetter.append(LETTER_MAP[num - 1]);
            getColLetter(index - 26, colLetter);

        }
        return colLetter.toString();
    }

    public static String getColLetter(int index) {
        StringBuffer colLetter = new StringBuffer();
        return getColLetter(index, colLetter);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 51; i++) {
            System.out.println(getColLetter(i));
        }
    }
}
