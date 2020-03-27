package com.alanpoi.common.config;

import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.common.util.ID;
import com.alanpoi.common.util.ServerID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class LoadConfig {

    @Bean(destroyMethod = "destroy")
    @ConditionalOnProperty(name = {"alanpoi.id.enable"}, havingValue = "true")
    @ConditionalOnBean(name = "jedisPool")
    public ServerID initID(JedisPool jedisPool) {
        ServerID serverID = new ServerID("ID", jedisPool);
        ID.config(serverID.getId());
        return serverID;
    }

    @Bean
    public ApplicationUtil getApplicationUtil() {
        return new ApplicationUtil();
    }
}
