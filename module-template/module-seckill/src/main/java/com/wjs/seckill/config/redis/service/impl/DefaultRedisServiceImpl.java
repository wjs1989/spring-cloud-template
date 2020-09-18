package com.wjs.seckill.config.redis.service.impl;

import com.wjs.seckill.config.redis.config.RedisKeyEnum;
import com.wjs.seckill.config.redis.model.BaseRedis;
import com.wjs.seckill.config.redis.model.RedisResult;
import com.wjs.seckill.config.redis.model.StringRedis;
import com.wjs.seckill.config.redis.service.BaseRedisHandler;
import com.wjs.seckill.config.redis.service.RedisService;
import com.wjs.seckill.config.redis.service.annotition.ReidsHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

        Objects.requireNonNull(instance, "未知的事件类型：" + dataType.name());
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
}
