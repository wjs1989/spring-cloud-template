package com.elasticsearch.core.config;

/**
 * @author wenjs
 */
public interface ConfigKey<V> {
    String getKey();

    default String getName() {
        return this.getKey();
    }

    default Class<V> getType() {
        return (Class<V>) Object.class;
    }

    static <T> ConfigKey<T> of(String key) {
        return of(key, key);
    }

    static <T> ConfigKey<T> of(String key, String name) {
        return (ConfigKey<T>) SimpleConfigKey.of(key, name, Object.class);
    }

    static <T> ConfigKey<T> of(String key, String name, Class<T> type) {
        return SimpleConfigKey.of(key, name, type);
    }

    default ConfigKeyValue<V> value(final V value) {
        return new ConfigKeyValue<V>() {
            @Override
            public V getValue() {
                return value;
            }

            @Override
            public String getKey() {
                return ConfigKey.this.getKey();
            }

            @Override
            public String getName() {
                return ConfigKey.this.getName();
            }

            @Override
            public Class<V> getType() {
                return ConfigKey.this.getType();
            }
        };
    }
}
