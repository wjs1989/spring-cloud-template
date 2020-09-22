package com.wjs.redis.service;

import com.wjs.redis.config.RedisKeyEnum;
import com.wjs.redis.model.BaseRedis;
import com.wjs.redis.model.RedisResult;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 11:59
 */
public interface RedisService {

    /**
     * 保存
     */
    void save(BaseRedis request);

    /**
     * 获取
     */
    RedisResult get(RedisKeyEnum redisKey);

    /**
     * 获取
     */
    RedisResult get(String redisKey);

    Boolean delete(String key);

    Long delete(Collection<Object> keys);

    Boolean expire(String key, long timeout, TimeUnit unit);

    Boolean expireAt(String key, Date date);

    Long getExpire(String key);

    Long getExpire(String key, TimeUnit unit);
}
