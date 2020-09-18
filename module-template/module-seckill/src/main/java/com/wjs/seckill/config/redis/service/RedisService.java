package com.wjs.seckill.config.redis.service;

import com.wjs.seckill.config.redis.config.RedisKeyEnum;
import com.wjs.seckill.config.redis.model.BaseRedis;
import com.wjs.seckill.config.redis.model.RedisResult;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 11:59
 */
public interface RedisService {
    void save(BaseRedis request);
    RedisResult get(RedisKeyEnum redisKey);
}
