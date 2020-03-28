package com.alanpoi.common.config;

import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.common.util.ID;
import com.alanpoi.common.util.ServerID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class LoadConfig {

    @Bean(destroyMethod = "destroy")
    @ConditionalOnBean(name = "jedisPool")
    public ServerID initID(JedisPool jedisPool) {
        ServerID serverID = new ServerID(jedisPool);
        return serverID;
    }

    @Bean
    public ID initID() {
        return new ID();
    }

    @Bean
    public ApplicationUtil getApplicationUtil() {
        return new ApplicationUtil();
    }
}
