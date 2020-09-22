package com.wjs.redis.model;

import org.springframework.data.redis.connection.DataType;

import java.util.HashMap;
import java.util.Map;

public class HashRedis extends BaseRedis {
    private String field;

    private Map<String,Object> body;

    public HashRedis(){
        super();
        this.dataType = DataType.HASH;
    }

    public HashRedis(String key, Object value){
        this(key,null,value,NO_EXPIRE);
    }

    public HashRedis(String key,String field, Object value){
        this(key,field,value,NO_EXPIRE);
    }

    public HashRedis(String key,String field,  Object value, long minExpire, long maxExpire){
        this(key,field,value,NO_EXPIRE);
        this.expire = getExpire((int)minExpire,(int)maxExpire);
    }

    public HashRedis(String key,String field, Object value, long expire){
        this();
        this.key = key;
        this.field = field;
        this.value = value;
        this.expire = expire;
    }
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public void put(String field,Object value){
        if(body == null){
            body = new HashMap<>();
        }
        body.put(field,value);
    }
}
