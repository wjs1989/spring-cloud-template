package com.wjs.produce.config.redis;

/**
 * redisKey 的顶级接口
 *
 * @author wenjs
 */
public interface BaseRedisKey {
    /**
     * 获取key
     * @return
     */
    String getKey();

    /**
     * 获取key的过期时间
     * @return
     */
    int getExpire();
}
