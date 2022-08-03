package com.alanpoi.common.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class CloseUtils {

    public static void close(Closeable closeable) throws IOException {
        if (null != closeable) {
            try {
                if (closeable instanceof OutputStream) {
                    ((OutputStream) closeable).flush();
                }
                closeable.close();
            } catch (IOException e) {
                throw e;
            }
        }
    }
}
