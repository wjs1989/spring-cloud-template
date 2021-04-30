package com.elasticsearch.core.config;

/**
 * @author wenjs
 */
public interface ConfigKeyValue<V> extends ConfigKey<V>  {
    V getValue();

    default boolean isNull() {
        return null == this.getValue();
    }

    default boolean isNotNull() {
        return null != this.getValue();
    }
}
