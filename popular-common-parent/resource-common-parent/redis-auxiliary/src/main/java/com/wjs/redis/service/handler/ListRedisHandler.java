package com.wjs.redis.service.handler;

import com.wjs.model.constant.Constant;
import com.wjs.redis.config.RedisKeyEnum;
import com.wjs.redis.model.BaseRedis;
import com.wjs.redis.model.ListRedis;
import com.wjs.redis.model.RedisResult;
import com.wjs.redis.service.BaseRedisHandler;
import com.wjs.redis.service.annotition.ReidsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@ReidsHandler("list")
public class ListRedisHandler implements BaseRedisHandler {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public RedisResult save(BaseRedis request) {
        saveList(request);
        return null;
    }

    @Override
    public RedisResult get(RedisKeyEnum redisKey) {
        return getList(redisKey.getKey());
    }

    @Override
    public RedisResult get(String redisKey) {
        return getList(redisKey);
    }

    private void saveList(BaseRedis request) {
        ListRedis listRedis = (ListRedis) request;

        Object value = listRedis.getValue();
        if (Objects.isNull(value)) {
            return;
        }
        if(Collection.class.isAssignableFrom(value.getClass())){
            redisTemplate.opsForList().leftPushAll(listRedis.getKey(),(Collection) value);
        }else{
            redisTemplate.opsForList().leftPushAll(listRedis.getKey(),value);
        }

        if (listRedis.getExpire() > Constant.ZERO) {
            redisTemplate.expire(listRedis.getKey(), listRedis.getExpire(), TimeUnit.SECONDS);
        }
    }

    private RedisResult getList(String redisKey) {
        RedisResult redisResult = new RedisResult();
        List<Object> range = redisTemplate.opsForList().range(redisKey, Constant.ZERO, Constant.MINUS_ONE);
        ListRedis listRedis = new ListRedis();
        listRedis.setValue(range);
        redisResult.setListRedis(listRedis);
        return redisResult;
    }


}
