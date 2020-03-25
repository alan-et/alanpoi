package com.alanpoi.core.config;

import com.alanpoi.core.util.ID;
import com.alanpoi.core.util.ServerID;
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
}
