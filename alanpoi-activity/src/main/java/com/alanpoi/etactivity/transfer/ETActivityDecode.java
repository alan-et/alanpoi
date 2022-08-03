package com.alanpoi.etactivity.transfer;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ETActivityDecode<T> implements Decode<T> {

    @Override
    public void decode(InputStream inputStream, T c) throws IOException {
        c = (T) JSONObject.parseObject(new String(toByteArray(inputStream)), c.getClass());
    }

    /**
     * InputStream 转换成byte[]
     *
     * @param input
     * @return
     * @throws IOException
     */
    private static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
