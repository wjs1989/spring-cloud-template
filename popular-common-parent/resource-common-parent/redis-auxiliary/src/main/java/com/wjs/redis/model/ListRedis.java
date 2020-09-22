package com.wjs.redis.model;

import org.springframework.data.redis.connection.DataType;

/**
 * @author wenjs
 */
public class ListRedis  extends BaseRedis {

    public ListRedis(){
        super();
        this.dataType = DataType.LIST;
    }

    public ListRedis(String key, Object value){
        this(key,value,NO_EXPIRE);
    }

    public ListRedis(String key, Object value, long expire){
        this();
        this.key = key;
        this.value = value;
        this.expire = expire;
    }

    public ListRedis(String key, Object value, long minExpire, long maxExpire){
        this(key,value,NO_EXPIRE);
        this.expire = getExpire((int)minExpire,(int)maxExpire);
    }
}
