package com.alanpoi.common.config;

import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.common.util.ID;
import com.alanpoi.common.util.ServerID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class LoadConfig {

    @Bean
    public ApplicationUtil getApplicationUtil() {
        return new ApplicationUtil();
    }

    @Bean(destroyMethod = "destroy")
    @ConditionalOnBean(name = "redisTemplate")
    @ConditionalOnProperty(name = {"alanpoi.serverid.enable"}, havingValue = "true")
    public ServerID initServerID(RedisTemplate redisTemplate) {
        ServerID serverID = new ServerID(redisTemplate);
        return serverID;
    }

    @Bean
    public ID initID() {
        return new ID();
    }
}
