package com.elasticsearch.core.config;

import java.beans.ConstructorProperties;

/**
 * @author wenjs
 */
public class SimpleConfigKey<V> implements ConfigKey<V> {
    private String key;
    private String name;
    private Class<V> type;

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Class<V> getType() {
        return this.type;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Class<V> type) {
        this.type = type;
    }

    @ConstructorProperties({"key", "name", "type"})
    private SimpleConfigKey(String key, String name, Class<V> type) {
        this.key = key;
        this.name = name;
        this.type = type;
    }

    public static <V> SimpleConfigKey<V> of(String key, String name, Class<V> type) {
        return new SimpleConfigKey(key, name, type);
    }
}
