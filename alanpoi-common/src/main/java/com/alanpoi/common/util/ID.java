package com.alanpoi.common.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * |16bit(serverId)|32bit(timestamp)|16bit(seqId)|
 */
public class ID {
    private static AtomicLong _id = null;

    public synchronized static void config(short serverId) {
        if (serverId == -1) throw new IllegalArgumentException("serverId can't to -1");
        if (serverId == 0) throw new IllegalArgumentException("serverId can't to 0");
        if (_id == null) {
            serverId &= 0x7FFF; //不能为负数
            int timestamp = (int) (System.currentTimeMillis() / 1000);

            long initialFactor = ((long) serverId) << 48 | (long) (timestamp) << 16;

            _id = new AtomicLong(initialFactor);
        }
    }

    public static long next() {
        if (_id == null) throw new IllegalStateException("ID state error");
        return _id.incrementAndGet();
    }
}
