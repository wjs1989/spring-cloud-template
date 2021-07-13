package com.wjs.oauth2.server.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 重写原生方法支持redis缓存
 *
 * @author wenjs
 */
public class RedisClientDetailsService extends JdbcClientDetailsService {
    private long TTL = 600L;
    private RedisTemplate<Object, Object> redisTemplate;

    public RedisClientDetailsService(DataSource dataSource, RedisTemplate<Object, Object> redisTemplate) {
        super(dataSource);
//        super.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
//        super.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);

        this.redisTemplate = redisTemplate;
    }

    @Override
    // @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        try {
            Object client = redisTemplate.opsForValue().get( "clientId:" + clientId);
            if (Objects.nonNull(client)) {

                ClientDetails details = JSONObject.parseObject(client.toString(), BaseClientDetails.class);
                return details;
            }
        } catch (Exception e) {
        }

        ClientDetails clientDetails = super.loadClientByClientId(clientId);

        try {
            redisTemplate.opsForValue().set("clientId:" + clientId,
                    JSONObject.toJSONString(clientDetails), TTL, TimeUnit.SECONDS);
        } catch (Exception e) {
        }

        return clientDetails;
    }
}
