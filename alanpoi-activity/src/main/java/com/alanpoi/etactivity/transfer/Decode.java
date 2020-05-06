package com.alanpoi.etactivity.transfer;

import java.io.IOException;
import java.io.InputStream;

public interface Decode<T> {
    void decode(InputStream inputStream, T c) throws IOException;
}
