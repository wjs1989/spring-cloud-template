package com.wjs.redis.service.handler;

import com.wjs.model.constant.Constant;
import com.wjs.redis.config.RedisKeyEnum;
import com.wjs.redis.model.BaseRedis;
import com.wjs.redis.model.MemberRedis;
import com.wjs.redis.model.RedisResult;
import com.wjs.redis.service.BaseRedisHandler;
import com.wjs.redis.service.annotition.ReidsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@ReidsHandler("set")
public class MemberRedisHandler implements BaseRedisHandler {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public RedisResult save(BaseRedis request) {
        saveMember(request);
        return null;
    }

    @Override
    public RedisResult get(RedisKeyEnum redisKey) {
        return getMember(redisKey.getKey());
    }

    @Override
    public RedisResult get(String redisKey) {
        return getMember(redisKey);
    }

    private void saveMember(BaseRedis request) {
        MemberRedis memberRedis = (MemberRedis) request;
        Object value = memberRedis.getValue();
        if (Objects.isNull(value)) {
            return;
        }

        if (Collection.class.isAssignableFrom(value.getClass())) {
            Collection collection = (Collection) value;
            Object[] newArr = collection.toArray(new Object[collection.size()]);
            redisTemplate.opsForSet().add(memberRedis.getKey(), newArr);
        } else {
            redisTemplate.opsForSet().add(memberRedis.getKey(), value);
        }

        if (memberRedis.getExpire() > Constant.ZERO) {
            redisTemplate.expire(memberRedis.getKey(), memberRedis.getExpire(), TimeUnit.SECONDS);
        }
    }

    private RedisResult getMember(String redisKey) {
        RedisResult redisResult = new RedisResult();

        Set<Object> members = redisTemplate.opsForSet().members(redisKey);
        MemberRedis memberRedis = new MemberRedis();
        memberRedis.setValue(members);

        redisResult.setMemberRedis(memberRedis);

        return redisResult;
    }
}
