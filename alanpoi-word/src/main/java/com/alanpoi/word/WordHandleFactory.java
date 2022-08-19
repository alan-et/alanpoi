package com.alanpoi.word;

import com.alanpoi.word.handle.WordHandle;

public class WordHandleFactory {

    private static volatile WordHandle wordHandle = null;

    public static WordHandle getWordHandle() {
        if (wordHandle == null) {
            synchronized (WordHandle.class) {
                if (wordHandle == null) {
                    wordHandle = new WordHandle();
                }
            }
        }
        return wordHandle;
    }
}
