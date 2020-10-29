package com.wjs.produce.process;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {

    private static ConcurrentHashMap<String,String> cache = new ConcurrentHashMap<>();


    public static void put(String key ,String value){
        cache.put(key,value);
    }

    public static ConcurrentHashMap<String,String> getCache(){
        return cache;
    }


}
