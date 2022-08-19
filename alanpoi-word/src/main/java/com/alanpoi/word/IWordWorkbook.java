package com.alanpoi.word;

import java.io.*;

public interface IWordWorkbook extends Closeable {

    void write(OutputStream stream) throws IOException;

    InputStream getInputStream() throws FileNotFoundException;

}
