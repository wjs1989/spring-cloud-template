package com.wjs.produce.config.redis;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    static Logger logger = LoggerFactory.getLogger(com.isky.visual.redis.RedisService.class);

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public DataType getKeyType(String key) {
        return redisTemplate.type(key);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Long delete(Collection<Object> keys) {
        return redisTemplate.delete(keys);
    }

    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }


    /**
     * 保存字符串数据
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error("RedisService.setString:{}", e);
        }
    }

    /**
     * 保存字符串数据
     *
     * @param key
     * @param value
     * @param timeout 单位s
     */
    public void setString(String key, String value, long timeout) {
        try {
            redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(timeout));
        } catch (Exception e) {
            logger.error("RedisService.setString:{}", e);
        }
    }
    public Boolean setNxString(String key, String value, long timeout) {
        try {
            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, value);
            if(aBoolean){
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return aBoolean;
        } catch (Exception e) {
            logger.error("RedisService.setString:{}", e);
        }
        return false;
    }

    /**
     * 获取字符串信息
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        try {
            Object object = redisTemplate.opsForValue().get(key);
            return Objects.isNull(object) ? "" : object.toString();
        } catch (Exception e) {
            logger.error("RedisService.getString:{}", e);
        }
        return Strings.EMPTY;
    }

    /**
     * 设置hash
     * @param key
     * @param value
     */
    public void setHash(String key, Map<String, Object> value) {
        try {
            redisTemplate.opsForHash().putAll(key, value);
        } catch (Exception e) {
            logger.error("RedisService.setHash:{}", e);
        }
    }

    /**
     * 设置hash
     * @param key
     * @param value
     * @param timeout 单位s
     */
    public void setHash(String key, Map<String, Object> value, long timeout) {
        try {
            setHash(key, value);
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("RedisService.setHash:{}", e);
        }
    }

    /**
     * 设置hash 指定字段
     * @param key
     * @param field
     * @param value
     */
    public void putHash(String key, String field, Object value) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
        } catch (Exception e) {
            logger.error("RedisService.putHash:{}", e);
        }
    }

    /**
     * 获取hash值
     * @param key
     * @return
     */
    public Map<Object, Object> getHash(String key) {
        try {
            Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
            return entries;
        } catch (Exception e) {
            logger.error("RedisService.getString:{}", e);
        }
        return null;
    }

}
