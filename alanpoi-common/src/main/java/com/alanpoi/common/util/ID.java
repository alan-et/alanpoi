package com.alanpoi.common.util;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 无规律分布式ID（支持高并发）
 * algorithm |8bit(serverId)|44bit(timestamp+version)|12bit(seqId)|
 *
 * @author pengzhuoxun
 * @since 1.1.2
 */
public class ID {

    /**
     * Service unique identification
     */
    private short serverId;

    /**
     * Timestamp (Timestamp (MS))
     */
    private long timestamp;

    private long seq;

    /**
     * operate version
     */
    private long version;

    private Random random;

    private AtomicInteger atomicInt;

    public ID() {
        random = new Random();
        init();
        ServerID serverID = ApplicationUtil.getBean(ServerID.class);
        if (serverID == null)
            serverId = (short) random.nextInt(0x7F);
        else
            serverId = serverID.getId();

    }

    private void init() {
        getTimestamp();
        getSeq();
        atomicInt = new AtomicInteger(1);
    }

    public long current() {
        return (0L | serverId) << 56 | timestamp + version | seq;
    }

    public long next() {
        return (0L | serverId) << 56 | (getTimestamp() + getVersion()) | getSeq();
    }

    public static ID getId() {
        return ApplicationUtil.getBean(ID.class);
    }

    private long getTimestamp() {
        return timestamp = System.currentTimeMillis() << 12;
    }

    private long getSeq() {
        return seq = 0L | random.nextInt(0xFF);
    }

    private long getVersion() {
        version = atomicInt.incrementAndGet();
        if (version > 0x7fffffff - 1) atomicInt = new AtomicInteger(1);
        return version = version << 12;
    }

    public static void main(String[] args) {
        List<Long> list = new AlanList<>();
        ID id = new ID();
        int i = 0;
        do {
            Long idLong = id.next();
            if (list.contains(idLong)) {
                System.out.println("重复:" + idLong);
                break;
            }
            System.out.println(idLong);
            list.add(idLong);
            i++;
        } while (i < 2000);
    }
}
