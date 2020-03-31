package com.alanpoi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 64位分布式ID(Long)（支持高并发(每毫秒32767，超过并发数，排队1毫秒)）
 * 支持时间  此方法可以支持到2248年(大概还有228年，应该是目前除开UUID(UUID是128为字节，32位的字符，生成的ID无法排序)，支持时间最长的分布式算法)
 * 支持节点  为了更好的支持高并发，适量的降低了服务器节点数，此方法最多支持64(0-63)台服务器
 * 容错率高
 * algorithm |43bit(timestamp)|15bit(version)|6bit(seqId)|
 *
 * @author pengzhuoxun
 * @since 1.1.2
 */
public class ID {

    private static Logger logger = LoggerFactory.getLogger(ID.class);

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

    private transient long modCount = 0;

    private transient String lock = "";

    public ID() {
        random = new Random();
        init();
        ServerID serverID = ApplicationUtil.getBean(ServerID.class);
        if (serverID == null)
            serverId = (short) random.nextInt(0x3f + 1);
        else
            serverId = serverID.getId();

    }

    private void init() {
        getTimestamp();
        getSeq();
        atomicInt = new AtomicInteger(0);
    }

    public long current() {
        return timestamp | version | (serverId | 0L);
    }

    public long next() {
        getVersion();
        return getTimestamp() | version | (serverId | 0L);
    }

    public static ID getId() {
        return ApplicationUtil.getBean(ID.class);
    }

    private long getTimestamp() {
        return timestamp = (System.currentTimeMillis() | 0L) << 20;
    }

    private long getSeq() {
        return seq = 0L | random.nextInt(0xfff);
    }

    private long getVersion() {
        version = atomicInt.incrementAndGet();
        if (version == 0x7fff) atomicInt = new AtomicInteger(0);
        if (version == 1 && modCount > 0) {
            modCount++;
            lock = String.valueOf(timestamp) + version;
            synchronized (lock) {
                try {
                    if (lock.equals(String.valueOf(System.currentTimeMillis() << 20) + version)) {
                        logger.warn("生成ID超过最大并发,系统正在排队处理,排队时间1毫秒,最大每毫米并发数:" + 0x7fff);
                        Thread.sleep(1);
                        getTimestamp();
                    }
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
                return version = (version | 0L) << 6;
            }
        } else {
            modCount++;
            return version = (version | 0L) << 6;
        }
    }

    public static void main(String[] args) {
        List<Long> list = new AlanList<>();
        System.out.println(list);
        ID id = new ID();
        int i = 0;
        do {
            Long idLong = id.next();
            if (list.contains(idLong)) {
                System.out.println("重复:" + idLong);
                break;
            }
            if (idLong < 0) {
                System.out.println("出现负数:" + idLong);
                break;
            }
            System.out.println(idLong);
            list.add(idLong);
            i++;
        } while (i < 0x7fffff);
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(Long.valueOf((0x7ffffffffffL) << 20) | (0x7fff | 0L) << 6 | 0x3f);
    }
}
