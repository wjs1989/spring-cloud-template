package com.wjs.seckill.config.redis.service.handler;

import com.wjs.seckill.config.redis.config.RedisKeyEnum;
import com.wjs.seckill.config.redis.model.BaseRedis;
import com.wjs.seckill.config.redis.model.RedisResult;
import com.wjs.seckill.config.redis.model.StringRedis;
import com.wjs.seckill.config.redis.service.BaseRedisHandler;
import com.wjs.seckill.config.redis.service.annotition.ReidsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ReidsHandler("string")
public class StringRedisHandler implements BaseRedisHandler {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public RedisResult save(BaseRedis request) {
        saveString(request);
        return null;
    }

    @Override
    public RedisResult get(RedisKeyEnum redisKey) {
        return null;
    }


    private RedisResult getString(String key){
        RedisResult redisResult = new RedisResult();

        Object object = redisTemplate.opsForValue().get(key);
        StringRedis stringRedis = new StringRedis();
        stringRedis.setValue(String.valueOf(object));

        redisResult.setString(stringRedis);
        return redisResult;
    }

    private void saveString(BaseRedis request){
        StringRedis srr = (StringRedis)request;
        redisTemplate.opsForValue().set(srr.getKey(),srr.getValue(), Duration.ofSeconds(request.getExpire()));
    }

}
