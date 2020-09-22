package com.wjs.redis.model;

import com.wjs.redis.config.RedisKeyEnum;
import org.springframework.data.redis.connection.DataType;

import java.util.Random;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 12:00
 */
public abstract class BaseRedis {
    protected static final long NO_EXPIRE = -1;

    /**
     * 数据类型
     */
    protected DataType dataType;
    protected String key;
    protected Object value;
    protected long expire;
    protected RedisKeyEnum redisKey;

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public RedisKeyEnum getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(RedisKeyEnum redisKey) {
        this.redisKey = redisKey;
    }

    protected int getExpire(int minExpire, int maxExpire) {
        Random r = new Random();
        return r.nextInt(maxExpire - minExpire + 1) + minExpire;
    }

    public static BaseRedis createRedis(RedisKeyEnum redisKey, Object value) {
        switch (redisKey.getDataType()) {
            case STRING: {
                StringRedis stringRedis = new StringRedis(redisKey.getKey(), value, redisKey.getExpire());
                stringRedis.setRedisKey(redisKey);
                return stringRedis;
            }
            case HASH:{
                HashRedis hashRedis = new HashRedis(redisKey.getKey(),null, value, redisKey.getExpire());
                hashRedis.setRedisKey(redisKey);
                return hashRedis;
            }
        }
        return null;
    }
}
