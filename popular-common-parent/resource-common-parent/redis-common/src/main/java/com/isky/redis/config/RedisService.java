package com.isky.redis.config;

import com.isky.redis.key.BaseRedisKey;
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

/**
 * redis 的操作服务类
 * 支撑 BaseRedisKey
 * @see BaseRedisKey
 *
 * 参考子类 RedisKeyEnum
 * <p>
 * RedisKeyEnum 包含随机的过期时间
 * </p>
 *
 * @author wenjs
 */
@Component
public class RedisService {
    static Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public DataType getKeyType(String key) {
        return redisTemplate.type(key);
    }

    public DataType getKeyType(BaseRedisKey key) {
        return getKeyType(key.getKey());
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public Boolean delete(BaseRedisKey key) {
        return delete(key.getKey());
    }

    public Long delete(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public Boolean expire(BaseRedisKey key, long timeout, TimeUnit unit) {
        return expire(key.getKey(), timeout, unit);
    }

    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    public Boolean expireAt(BaseRedisKey key, Date date) {
        return expireAt(key.getKey(), date);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public Long getExpire(BaseRedisKey key) {
        return getExpire(key.getKey());
    }

    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    public Long getExpire(BaseRedisKey key, TimeUnit unit) {
        return getExpire(key.getKey(), unit);
    }

    /**
     * 保存字符串数据
     * @param key
     * @param suffix 后缀
     * @param value
     */
    public void setString(BaseRedisKey key, Object suffix, String value) {
        setString(key.getKey() + suffix, value, key.getExpire());
    }

    /**
     * 保存字符串数据
     *
     * @param key
     * @param value
     */
    public void setString(BaseRedisKey key, String value) {
        setString(key.getKey(), value, key.getExpire());
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
            if (aBoolean) {
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
     * @param suffix 后缀
     * @return
     */
    public String getString(BaseRedisKey key ,Object suffix) {
        return getString(key.getKey()+suffix);
    }
    /**
     * 获取字符串信息
     *
     * @param key
     * @return
     */
    public String getString(BaseRedisKey key) {
        return getString(key.getKey());
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
     *
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
     *
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
     * 设置hash
     *
     * @param key
     * @param value
     * @param timeout 单位s
     */
    public void setHash(BaseRedisKey key, Map<String, Object> value, long timeout) {
        setHash(key.getKey(), value, key.getExpire());
    }

    /**
     * 设置hash 指定字段
     *
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
     * 设置hash 指定字段
     *
     * @param key
     * @param field
     * @param value
     */
    public void putHash(BaseRedisKey key, String field, Object value) {
        putHash(key.getKey(), field, value);
    }

    /**
     * 获取hash值
     *
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

    /**
     * 获取hash值
     *
     * @param key
     * @return
     */
    public Map<Object, Object> getHash(BaseRedisKey key) {
        return getHash(key.getKey());
    }

}
