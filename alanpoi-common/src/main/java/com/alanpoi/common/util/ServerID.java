package com.alanpoi.common.util;

import com.alanpoi.common.constants.TimeConstants;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ServerID {
    private static Logger logger = LoggerFactory.getLogger(ServerID.class);
    public static final String KEY_SERVER_INFO = "serverInfo";
    public static final long SERVER_INFO_EXPIRE_TIME = TimeConstants.MINS_10 * 1000; //millisecond

    private static final ScheduledExecutorService executorService =
            Executors.newScheduledThreadPool(1, r -> {
                Thread thread = new Thread(r, "ServerID-Checker");
                thread.setDaemon(true);
                return thread;
            });

    private volatile static ServerID current = null;

    private String space = "alanpoi-g-serverid";

    private volatile ServerInfo info;

    private RedisTemplate redisTemplate;

    public ServerID(RedisTemplate redisTemplate) {
        if (current != null) throw new RuntimeException("ServerID can only create one");
        synchronized (ServerID.class) {
            if (current == null) {
                current = init(space, redisTemplate);
            }
        }
    }


    private ServerID init(String space, RedisTemplate redisTemplate) {

        this.space = space;
        this.redisTemplate = redisTemplate;

        String localIP = NetworkUtil.getLocalIP();
        List<ServerInfo> serverInfoList = getServerInfo(space);

        cleanExpire(serverInfoList, space);
        Optional<ServerInfo> current = findLocal(localIP, serverInfoList);

        this.info = current.orElseGet(() -> register(localIP, space));

        startCheckThread();
        return this;
    }

    private Optional<ServerInfo> findLocal(String localIP, List<ServerInfo> list) {
        return list.stream().filter(info -> info.getIp().equals(localIP)).findAny();
    }

    private void cleanExpire(List<ServerInfo> list, String space) {
        long now = System.currentTimeMillis();
        List<ServerInfo> expireList = list.stream()
                .filter(info -> now - info.getHbtime() > SERVER_INFO_EXPIRE_TIME)
                .collect(Collectors.toList());

        if (expireList.size() > 0) {
            String[] expireIds = expireList.stream()
                    .map(info -> String.valueOf(info.getId()))
                    .toArray(String[]::new);
            String key = genKey(space);
            redisTemplate.opsForHash().delete(key, expireIds);

            list.removeAll(expireList);
        }
    }


    private void startCheckThread() {
        //30秒心跳一次
        executorService.scheduleWithFixedDelay(this::heartbeat, 10, 30, TimeUnit.SECONDS);
    }

    public void heartbeat() {
        try {
            String key = genKey(space);
            current.info.setHbtime(System.currentTimeMillis());
            redisTemplate.opsForHash().put(key, String.valueOf(current.info.getId()), JSON.toJSONString(current.info));
        } catch (Exception e) {
            logger.warn("heartbeat error, ip:[{}] ", current.info.ip, e);
        }
    }

    public void destroy() {
        try {
            String key = genKey(space);
            redisTemplate.opsForHash().delete(key, String.valueOf(current.info.getId()));

        } catch (Exception e) {
            logger.warn("destroy error, ip:[{}] ", current.info.ip, e);
        }
        executorService.shutdown();
    }

    public short getId() {
        return info.getId();
    }

    private ServerInfo register(String localIP, String space) {
        logger.info("service ip:{}", localIP);
        Random random = new Random();
        long now = System.currentTimeMillis();

        ServerInfo info = new ServerInfo();

        info.setIp(localIP);
        info.setHbtime(now);
        info.setStarttime(now);
        String key = genKey(space);

        int retryTimes = 1000;
        for (int i = 0; i < retryTimes; i++) {
            //short 1-32767
            short id = (short) (random.nextInt(0x7F) + 1);
            info.setId(id);
            boolean bool = redisTemplate.opsForHash().putIfAbsent(key, String.valueOf(id), JSON.toJSONString(info));
            if (bool) return info;
        }
        List<String> list = redisTemplate.opsForHash().values(key);
        if (!CollectionUtils.isEmpty(list) && list.size() >= 0x7F + 1) {
            throw new RuntimeException("register serverId error,Maximum number of server nodes exceeded,max node num: " + 0x3f + 1);
        } else {
            int k = 0;
            do {
                if (!list.contains(k)) {
                    info.setId((short) k);
                    return info;
                }
            } while (k <= 0x7F);
        }
        throw new RuntimeException("register serverId error " + localIP);
    }

    private List<ServerInfo> getServerInfo(String space) {
        String key = genKey(space);
        List<String> list = redisTemplate.opsForHash().values(key);
        return list.stream().map(s -> JSON.parseObject(s, ServerInfo.class)).collect(Collectors.toList());

    }

    private String genKey(String space) {
        return space + ":" + KEY_SERVER_INFO;
    }

    public static final class ServerInfo {
        private volatile short id;
        private volatile String ip;
        private volatile long starttime;
        private volatile long hbtime;

        public short getId() {
            return id;
        }

        public void setId(short id) {
            this.id = id;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public long getStarttime() {
            return starttime;
        }

        public void setStarttime(long starttime) {
            this.starttime = starttime;
        }

        public long getHbtime() {
            return hbtime;
        }

        public void setHbtime(long hbtime) {
            this.hbtime = hbtime;
        }
    }

}


