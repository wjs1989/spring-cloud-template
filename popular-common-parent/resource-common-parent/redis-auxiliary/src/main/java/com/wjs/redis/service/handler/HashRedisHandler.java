package com.wjs.redis.service.handler;

import com.wjs.model.constant.Constant;
import com.wjs.model.util.MapUtils;
import com.wjs.redis.config.RedisKeyEnum;
import com.wjs.redis.model.BaseRedis;
import com.wjs.redis.model.HashRedis;
import com.wjs.redis.model.RedisResult;
import com.wjs.redis.service.BaseRedisHandler;
import com.wjs.redis.service.annotition.ReidsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@ReidsHandler("hash")
public class HashRedisHandler implements BaseRedisHandler {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public RedisResult save(BaseRedis request) {
        saveHash(request);
        return null;
    }

    @Override
    public RedisResult get(RedisKeyEnum redisKey) {
        return  getHash(redisKey.getKey());
    }

    @Override
    public RedisResult get(String redisKey) {
        return getHash(redisKey);
    }

    private void saveHash(BaseRedis request) {
        HashRedis hashRedis = (HashRedis) request;

        String field = hashRedis.getField();
        Object value = hashRedis.getValue();
        Map<String, Object> body = hashRedis.getBody();
        if (Objects.isNull(value) && (Objects.isNull(body) || body.isEmpty())) {
            return;
        }

        if ("".equals(field) || null == field) {
            Map map = getMap(hashRedis);
            redisTemplate.opsForHash().putAll(hashRedis.getKey(), map);
        } else {
            redisTemplate.opsForHash().put(hashRedis.getKey(), field, value);
        }

        if (hashRedis.getExpire() > Constant.ZERO) {
            redisTemplate.expire(hashRedis.getKey(), hashRedis.getExpire(), TimeUnit.SECONDS);
        }
    }

    private RedisResult getHash(String redisKey) {
        RedisResult redisResult = new RedisResult();

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(redisKey);
        HashRedis hashRedis = new HashRedis();
        hashRedis.setValue(entries);

        redisResult.setHashRedis(hashRedis);
        return redisResult;
    }


    private Map getMap(HashRedis hashRedis) {
        Map map = MapUtils.convertToMap(hashRedis.getValue());
        Map<String, Object> body = hashRedis.getBody();
        if (!Objects.isNull(body)) {
            map.putAll(body);
        }
        return map;
    }
}
