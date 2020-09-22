package com.wjs.redis.model;

import org.springframework.data.redis.connection.DataType;

public class MemberRedis extends BaseRedis {
    public MemberRedis(){
        super();
        this.dataType = DataType.SET;
    }

    public MemberRedis(String key, Object value){
        this(key,value,NO_EXPIRE);
    }

    public MemberRedis(String key, Object value, long expire){
        this();
        this.key = key;
        this.value = value;
        this.expire = expire;
    }

    public MemberRedis(String key, Object value, long minExpire, long maxExpire){
        this(key,value,NO_EXPIRE);
        this.expire = getExpire((int)minExpire,(int)maxExpire);
    }
}
