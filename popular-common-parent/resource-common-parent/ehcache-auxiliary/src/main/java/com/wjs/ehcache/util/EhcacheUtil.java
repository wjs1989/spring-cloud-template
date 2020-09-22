package com.wjs.ehcache.util;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;
import java.util.Objects;

public class EhcacheUtil {
    private static final String path = "/ehcache.xml";

    private static EhcacheUtil ecCache;

    private CacheManager manager;

    private String defalutCacheName = "defaultCache";

    private EhcacheUtil(String path) {
        URL url = getClass().getResource(path);
        manager = CacheManager.create(url);
    }

    public static EhcacheUtil getInstance() {
        return EhEhcacheBuild.ehCache;
    }

    public static EhcacheUtil getInstance(String filePath) {
        if (Objects.isNull(ecCache)) {
            synchronized (EhcacheUtil.class) {
                if (Objects.isNull(ecCache)) {
                    return new EhcacheUtil(filePath);
                }
            }
        }
        return ecCache;
    }

    public void put(String cacheName, String key, Object value) {
        Cache cache = getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    public Object get(String cacheName, String key) {
        Cache cache = getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public Cache getCache(String cacheName) {
        Cache cache = manager.getCache(cacheName);
        if (Objects.isNull(cache)) {
            return manager.getCache(defalutCacheName);
        }
        return cache;
    }

    public void remove(String cacheName, String key) {
        Cache cache = getCache(cacheName);
        cache.remove(key);
    }


    public void put(String key, Object value) {
        put(defalutCacheName, key, value);
    }

    public Object get(String key) {
        return get(defalutCacheName, key);
    }

    public void setDefalutCacheName(String defalutCacheName) {
        this.defalutCacheName = defalutCacheName;
    }

    private static class EhEhcacheBuild {
        private static EhcacheUtil ehCache = new EhcacheUtil(path);
    }
}
