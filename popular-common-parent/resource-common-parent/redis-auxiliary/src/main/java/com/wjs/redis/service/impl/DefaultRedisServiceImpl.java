package com.wjs.redis.service.impl;

import com.wjs.redis.config.RedisKeyEnum;
import com.wjs.redis.model.BaseRedis;
import com.wjs.redis.model.RedisResult;
import com.wjs.redis.service.BaseRedisHandler;
import com.wjs.redis.service.RedisService;
import com.wjs.redis.service.annotition.ReidsHandler;
import com.wjs.redis.service.handler.RedisExecutorDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 11:59
 */
@Service
public class DefaultRedisServiceImpl implements RedisService {

    private Map<DataType, Consumer<? extends BaseRedis>> typeSaveCache = new HashMap<>();
    private Map<DataType, Function<String, RedisResult>> typeGetCache = new HashMap<>();

    private Map<DataType, BaseRedisHandler> handlerCache = new HashMap<>();

    @Autowired
    private RedisExecutorDelegate redisExecutorDelegate;

    @Autowired
    public void init(List<BaseRedisHandler> handlers) {
        if (handlers != null && !handlers.isEmpty()) {
            for (BaseRedisHandler handler : handlers) {
                ReidsHandler annotation = handler.getClass().getAnnotation(ReidsHandler.class);
                if (annotation != null) {
                    handlerCache.put(DataType.fromCode(annotation.value()), handler);
                }
            }
        }
    }

    public BaseRedisHandler getEventHandler(DataType dataType) {
        Objects.requireNonNull(handlerCache, "redis处理器未初始化");
        BaseRedisHandler instance = handlerCache.get(dataType);

        Objects.requireNonNull(instance, "未知的redis处理器类型：" + dataType.name());
        return instance;
    }

    @Override
    public void save(BaseRedis request){
        getEventHandler(request.getDataType()).save(request);
    }

    @Override
    public RedisResult get(RedisKeyEnum redisKey){
        return getEventHandler(redisKey.getDataType()).get(redisKey);
    }

    @Override
    public RedisResult get(String redisKey) {
        DataType keyType = redisExecutorDelegate.getKeyType(redisKey);
        return getEventHandler(keyType).get(redisKey);
    }

    @Override
    public Boolean delete(String key) {
        return redisExecutorDelegate.delete(key);
    }

    @Override
    public Long delete(Collection<Object> keys) {
        return redisExecutorDelegate.delete(keys);
    }

    @Override
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisExecutorDelegate.expire(key,timeout,unit);
    }

    @Override
    public Boolean expireAt(String key, Date date) {
        return redisExecutorDelegate.expireAt(key,date);
    }

    @Override
    public Long getExpire(String key) {
        return redisExecutorDelegate.getExpire(key);
    }

    @Override
    public Long getExpire(String key, TimeUnit unit) {
        return redisExecutorDelegate.getExpire(key,unit);
    }

}
