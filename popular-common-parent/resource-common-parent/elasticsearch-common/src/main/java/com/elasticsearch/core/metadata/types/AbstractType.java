package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.config.ConfigKey;
import com.elasticsearch.core.config.ConfigKeyValue;
import com.elasticsearch.core.metadata.DataType;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjs
 */
public abstract class AbstractType<R> implements DataType {

    private Map<String, Object> expands;
    private String description;

    public AbstractType() {
    }

    public R expands(Map<String, Object> expands) {
        if (CollectionUtils.isEmpty(expands)) {
            return (R) this;
        } else {
            if (this.expands == null) {
                this.expands = new HashMap();
            }

            this.expands.putAll(expands);
            return (R) this;
        }
    }

    public R expand(ConfigKeyValue<?>... kvs) {
        if (!(kvs == null || kvs.length == 0)) {
            for (int i = 0; i < kvs.length; i++) {
                ConfigKeyValue<?> kv = kvs[i];
                this.expand(kv.getKey(), kv.getValue());
            }
        }
        return (R) this;
    }

    public <V> R expand(ConfigKey<V> configKey, V value) {
        return this.expand(configKey.getKey(), value);
    }

    public R expand(String configKey, Object value) {
        if (value == null) {
            return (R) this;
        } else {
            if (this.expands == null) {
                this.expands = new HashMap();
            }

            this.expands.put(configKey, value);
            return (R) this;
        }
    }

    public R description(String description) {
        this.description = description;
        return (R) this;
    }

    @Override
    public Map<String, Object> getExpands() {
        return this.expands;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setExpands(Map<String, Object> expands) {
        this.expands = expands;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
