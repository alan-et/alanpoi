package com.alanpoi.etactivity.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayDeque;
import java.util.Queue;

public class ByteBufCache {
    private static Queue<ByteBuf> byteBufCache = new ArrayDeque<>();

    public static void init() {
        for (int i = 0; i < 5; i++) {
            byteBufCache.add(Unpooled.buffer(32));
        }
    }

    public static synchronized ByteBuf getByteBuf() {
        if (byteBufCache.size() <= 0) {
            init();
        }
        return byteBufCache.poll();
    }

}
