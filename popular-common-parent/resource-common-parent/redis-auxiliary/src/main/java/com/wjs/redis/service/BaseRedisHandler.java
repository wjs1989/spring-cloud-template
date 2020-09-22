package com.wjs.redis.service;

import com.wjs.redis.config.RedisKeyEnum;
import com.wjs.redis.model.BaseRedis;
import com.wjs.redis.model.RedisResult;

public interface BaseRedisHandler {
    RedisResult save(BaseRedis request);
    RedisResult get(RedisKeyEnum redisKey);
    RedisResult get(String redisKey);
}
