package com.wjs.redis.model;

import org.springframework.data.redis.connection.DataType;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 13:40
 */
public class StringRedis extends BaseRedis {

    public StringRedis(){
        super();
        this.dataType = DataType.STRING;
    }

    public StringRedis(String key, Object value){
        this(key,value,NO_EXPIRE);
    }

    public StringRedis(String key, Object value, long expire){
        this();
        this.key = key;
        this.value = value;
        this.expire = expire;
    }

    public StringRedis(String key, Object value, long minExpire, long maxExpire){
        this(key,value,NO_EXPIRE);
        this.expire = getExpire((int)minExpire,(int)maxExpire);
    }
}
