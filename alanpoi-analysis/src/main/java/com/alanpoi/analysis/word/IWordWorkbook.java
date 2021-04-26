package com.alanpoi.analysis.word;

import java.io.*;

public interface IWordWorkbook extends Closeable{

    void write(OutputStream stream) throws IOException;

}
