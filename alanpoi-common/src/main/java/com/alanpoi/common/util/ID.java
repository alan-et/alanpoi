package com.alanpoi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 64位分布式ID(Long)（支持高并发(单节点每毫秒16383，超过并发数，排队1毫秒)）
 * 支持时间  此方法可以支持139年(应该是目前除开UUID(UUID是128为字节，32位的字符，生成的ID无法排序)，支持时间最长的分布式算法)
 * 支持节点  为了更好的支持高并发，适量的降低了服务器节点数，此方法最多支持128(0-127)台服务器
 * 容错率高  高并发的场景往往不能保持原子性，一般很容易出现问题，因此此算法引入了AtomicInteger（原子类，CAS算法）来解决此问题
 * algorithm |42bit(timestamp)|14bit(version)|7bit(serverId)|
 *
 * @author pengzhuoxun
 * @since 1.1.2
 */
public class ID {

    private static volatile ID defaultId = null;

    private static Logger logger = LoggerFactory.getLogger(ID.class);

    /**
     * Service unique identification
     */
    private short serverId;

    /**
     * 2020-4-1 00:00:00.000
     */
    private long begin = 1585699200000L;
//    private long begin = 1585624432663L;


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
        init(null);
    }

    public ID(ServerID serverID) {
        init(serverID);
    }

    public ID(long begin) {
        this.begin = begin;
        init(null);
    }

    private void init(ServerID serverID) {
        atomicInt = new AtomicInteger(0);
        random = new Random();
        getTimestamp();
        getSeq();
//        ServerID serverID = ApplicationUtil.getBean(ServerID.class);
        if (serverID == null)
            serverId = (short) random.nextInt(0x7f + 1);
        else
            serverId = serverID.getId();
    }

    public long current() {
        return timestamp | version | (serverId | 0L);
    }

    public long next() {

        return getTimestamp() | getVersion() | (serverId | 0L);
    }

    public static ID getId() {
        ID id = ApplicationUtil.getBean(ID.class);
        if (id == null) {
            if (defaultId == null) {
                synchronized (ID.class) {
                    if (defaultId == null) {
                        defaultId = new ID();
                    }
                }
            }
            return defaultId;
        }
        return id;
    }

    private long getTimestamp() {
        return timestamp = ((long) (System.currentTimeMillis() - begin) | 0L) << 21;
    }

    private long getSeq() {
        return seq = 0L | random.nextInt(0xfff);
    }

    private long getVersion() {
        long version = atomicInt.incrementAndGet();
        if (version == 0x3fff) atomicInt = new AtomicInteger(0);
        if (version == 1 && modCount > 0) {
            modCount++;
            lock = String.valueOf(timestamp) + version;
            synchronized (lock) {
                try {
                    if (lock.equals(String.valueOf((System.currentTimeMillis() - begin) << 21) + version)) {
                        logger.warn("生成ID超过最大并发,系统正在排队处理,排队时间1毫秒,最大每毫米并发数:" + 0x3fff);
                        Thread.sleep(1);
                        getTimestamp();
                    }
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
                return this.version = (version | 0L) << 7;
            }
        } else {
            modCount++;
            return this.version = (version | 0L) << 7;
        }
    }
}
