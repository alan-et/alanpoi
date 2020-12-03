package com.alanpoi.common.config;

import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.common.util.ID;
import com.alanpoi.common.util.ServerID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;

/**
 * @author pengzhuoxun
 */
@Configuration
public class LoadConfig {

    @Bean
    public ApplicationUtil getApplicationUtil() {
        return new ApplicationUtil();
    }

    @Bean(destroyMethod = "destroy")
//    @ConditionalOnBean(name = "redisTemplate")
    @ConditionalOnClass(RedisOperations.class)
    @ConditionalOnProperty(name = {"alanpoi.serverid.enable"}, havingValue = "true")
    public ServerID initServerID(StringRedisTemplate redisTemplate) {
        ServerID serverID = new ServerID(redisTemplate);
        return serverID;
    }

    @Bean
    public ID initID() {
        return new ID();
    }
}
