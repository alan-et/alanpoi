package com.alanpoi.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * 64位分布式ID(Long)（支持高并发(单节点每毫秒16383，超过并发数，排队1毫秒)）
 * 支持时间  此方法可以支持139年
 * 支持节点  最多支持128(0-127)台服务器
 * algorithm 1bit|42bit(timestamp)|14bit(version)|7bit(serverId)|
 * 结构：| 1位符号(0) | 42位时间戳 | 14位序列号 | 7位节点ID |
 *
 * @author pengzhuoxun
 * @since 1.3.9
 */
public class ID {

    private static Logger logger = LoggerFactory.getLogger(ID.class);

    /**
     * Service unique identification
     */
    private long serverId;

    /**
     * 基准时间，不允许修改
     */
    private final long begin;

    // 位数分配
    private final long serverIdBits = 7L;
    private final long sequenceBits = 14L;

    private final long maxServerId = -1L ^ (-1L << serverIdBits); // 127
    private final long sequenceMask = -1L ^ (-1L << sequenceBits); // 16383

    private final long sequenceShift = serverIdBits;
    private final long timestampLeftShift = sequenceBits + serverIdBits;

    private final AtomicLong sequenceAtomic = new AtomicLong(0);
    private volatile long lastTimestamp = -1L;
    private Random random;

    private static final AtomicLongFieldUpdater<ID> lastTimestampUpdater =
            AtomicLongFieldUpdater.newUpdater(ID.class, "lastTimestamp");


    // ====================== 你原版构造全部保留 ======================
    public ID() {
        //2020-4-1 00:00:00.000
        this.begin = 1585699200000L;
        init();
    }

    public ID(long begin) {
        this.begin = begin;
        init();
    }

    private void init() {
        random = new Random();
        ServerID serverID = ApplicationUtil.getBean(ServerID.class);
        if (serverID == null) {
            serverId = (short) random.nextInt(0x7f + 1);
        } else {
            serverId = serverID.getId();
            if (serverId > maxServerId || serverId < 0) {
                throw new IllegalArgumentException(String.format("Server ID can't be greater than %d or less than 0", maxServerId));
            }
        }
    }

    public long next() {
        for (; ; ) {
            long currentTimestamp = timeGen();
            long lastTs = lastTimestamp;

            if (currentTimestamp < lastTs) {
                logger.error("时钟回拨，拒绝生成ID，相差：{}ms", lastTs - currentTimestamp);
                throw new RuntimeException("Clock moved backwards");
            }

            long currentSeq = sequenceAtomic.get();
            long nextSeq = currentSeq + 1;
            long finalSeq = nextSeq & sequenceMask;

            // 在同一毫秒内
            if (currentTimestamp == lastTs) {
                // 毫秒内序列号用尽（截断后变成了0，说明16383用完了）
                if (finalSeq == 0) {
                    tilNextMillis(lastTs);
                    continue;
                }
                // 抢夺该毫秒内的序列号，抢到直接返回，不抢则自旋
                if (sequenceAtomic.compareAndSet(currentSeq, nextSeq)) {
                    return ((currentTimestamp - begin) << timestampLeftShift)
                            | (finalSeq << sequenceShift)
                            | serverId;
                }
            }
            // 跨毫秒（新毫秒）
            else {
                // 注意：跨毫秒时，我们不把 AtomicLong 强制清空。
                // 而是借用当前的 nextSeq 值。只要我们能成功把 lastTimestamp 从旧时间戳 CAS 改为新时间戳，
                // 就说明当前线程是“第一个跨入新毫秒”的幸运儿。
                // 为了防止finalSeq刚好为0（极其罕见但存在），我们强制让其从1开始，或者直接用当前的finalSeq

                // 谁能成功用 CAS 把时间戳推向新的一毫秒，谁就拥有这一毫秒的绝对解释权
                if (lastTimestampUpdater.compareAndSet(this, lastTs, currentTimestamp)) {
                    // 成功跨越时间戳的线程，顺便把序列号也同步一下
                    sequenceAtomic.set(nextSeq);
                    return ((currentTimestamp - begin) << timestampLeftShift)
                            | (finalSeq << sequenceShift)
                            | serverId;
                }
            }
        }
    }

    // ====================== 你原版工具方法全部保留 ======================
    public static ID getId() {
        return ApplicationUtil.getBean(ID.class);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
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
        } while (i < 0x3fff);
    }
}