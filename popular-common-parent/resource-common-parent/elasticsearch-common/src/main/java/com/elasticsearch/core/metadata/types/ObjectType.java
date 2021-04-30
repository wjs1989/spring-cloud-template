package com.elasticsearch.core.metadata.types;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elasticsearch.core.metadata.*;
import com.elasticsearch.util.BeanUtils;
import com.elasticsearch.util.FastBeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BiFunction;

import com.elasticsearch.core.metadata.ValidateResult;

/**
 * @author wenjs
 */
public class ObjectType extends AbstractType<ObjectType> implements DataType, Converter<Map<String, Object>> {
    private static final Logger log = LoggerFactory.getLogger(ObjectType.class);
    public static final String ID = "object";
    private List<PropertyMetadata> properties;

    public ObjectType() {
    }

    @Override
    public String getId() {
        return "object";
    }

    @Override
    public String getName() {
        return "对象类型";
    }

    public ObjectType addPropertyMetadata(PropertyMetadata property) {
        if (this.properties == null) {
            this.properties = new ArrayList();
        }

        this.properties.add(property);
        return this;
    }

    public List<PropertyMetadata> getProperties() {
        return this.properties == null ? Collections.emptyList() : this.properties;
    }

    public ObjectType addProperty(String property, DataType type) {
        return this.addProperty(property, property, type);
    }

    public ObjectType addProperty(String property, String name, DataType type) {
        SimplePropertyMetadata metadata = new SimplePropertyMetadata();
        metadata.setId(property);
        metadata.setName(name);
        metadata.setValueType(type);
        return this.addPropertyMetadata(metadata);
    }

    @Override
    public ValidateResult validate(Object value) {
        if (this.properties != null && !this.properties.isEmpty()) {
            Map<String, Object> mapValue = this.convert(value);

            for (PropertyMetadata property : properties) {
                Object data = mapValue.get(property.getId());
                if (data != null) {
                    ValidateResult result = property.getValueType().validate(data);
                    if (!result.isSuccess()) {
                        return result;
                    }
                }
            }
            return ValidateResult.success(mapValue);
        } else {
            return ValidateResult.success(value);
        }
    }

    @Override
    public JSONObject format(Object value) {
        return new JSONObject(this.handle(value, FormatSupport::format));
    }

    public Map<String, Object> handle(Object value, BiFunction<DataType, Object, Object> mapping) {
        if (value == null) {
            return null;
        } else {
            if (value instanceof String && ((String) value).startsWith("{")) {
                value = JSON.parseObject(String.valueOf(value));
            }

            if (!(value instanceof Map)) {
                Map<String, Object> map = new HashMap();
                BeanUtils.copyProperties(value, map);
                value = map;
            }

            if (!(value instanceof Map)) {
                return null;
            } else {
                Map<String, Object> mapValue = new HashMap((Map) value);
                if (this.properties != null) {
                    properties.forEach(property->{
                        Object data = mapValue.get(property.getId());
                        DataType type = property.getValueType();
                        if (data != null) {
                            mapValue.put(property.getId(), mapping.apply(type, data));
                        }
                    });
                }

                return mapValue;
            }
        }
    }

    @Override
    public Map<String, Object> convert(Object value) {
        return this.handle(value, (type, data) -> {
            return type instanceof Converter ? ((Converter) type).convert(data) : data;
        });
    }

    public Optional<PropertyMetadata> getProperty(String key) {
        return CollectionUtils.isEmpty(this.properties) ? Optional.empty() : this.properties.stream().filter((prop) -> {
            return prop.getId().equals(key);
        }).findAny();
    }

    public void setProperties(List<PropertyMetadata> properties) {
        this.properties = properties;
    }
}
