package com.wjs.seckill.config.redis.service;

import com.wjs.seckill.config.redis.config.RedisKeyEnum;
import com.wjs.seckill.config.redis.model.BaseRedis;
import com.wjs.seckill.config.redis.model.RedisResult;

public interface BaseRedisHandler {
    RedisResult save(BaseRedis request);
    RedisResult get(RedisKeyEnum redisKey);
}
