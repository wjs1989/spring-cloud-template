package com.wjs.produce.config.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenjs
 * @Description:
 * @date 2020/7/28 11:48
 */
@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate( RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //使用fastjson序列化
         FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(new DefaultStrSerializer());
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new DefaultStrSerializer());
        template.setHashKeySerializer(new DefaultStrSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(  RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    //
    // public RedisSentinelConfiguration redisSentinelConfiguration() {
    //     RedisSentinelConfiguration sentinel = new RedisSentinelConfiguration();
    //
    //     RedisNode marst = new RedisNode("10.204.125.150", 26379);
    //
    //     List<RedisNode> sentinels = new ArrayList<>();
    //     sentinels.add(new RedisNode("10.204.125.150", 26379));
    //     sentinels.add(new RedisNode("10.204.125.150", 26380));
    //     sentinels.add(new RedisNode("10.204.125.150", 26381));
    //
    //     sentinel.setSentinels(sentinels);
    //     sentinel.setMaster("master001");
    //     return sentinel;
    // }
}
