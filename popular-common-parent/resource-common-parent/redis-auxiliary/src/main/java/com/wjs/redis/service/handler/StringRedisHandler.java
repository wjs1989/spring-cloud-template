package com.wjs.redis.service.handler;

import com.alibaba.fastjson.JSONObject;
import com.wjs.model.constant.Constant;
import com.wjs.redis.config.RedisKeyEnum;
import com.wjs.redis.model.BaseRedis;
import com.wjs.redis.model.RedisResult;
import com.wjs.redis.model.StringRedis;
import com.wjs.redis.service.BaseRedisHandler;
import com.wjs.redis.service.annotition.ReidsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

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
        return getString(redisKey.getKey());
    }

    @Override
    public RedisResult get(String redisKey) {
        return getString(redisKey);
    }

    private RedisResult getString(String key){
        RedisResult redisResult = new RedisResult();

        Object object = redisTemplate.opsForValue().get(key);
        StringRedis stringRedis = new StringRedis();
        stringRedis.setValue(String.valueOf(object));

        redisResult.setStringRedis(stringRedis);
        return redisResult;
    }

    private void saveString(BaseRedis request){
        Object value = request.getValue();
        if(Objects.isNull(value)){
            return ;
        }

        String saveValue = null;
        if(value.getClass().isInstance(String.class)){
            saveValue = value.toString();
        }else{
            saveValue = JSONObject.toJSONString(value);
        }

        if(request.getExpire() < Constant.ZERO){
            redisTemplate.opsForValue().set(request.getKey(),saveValue);
        }else{
            redisTemplate.opsForValue().set(request.getKey(),saveValue, Duration.ofSeconds(request.getExpire()));
        }
    }

}
