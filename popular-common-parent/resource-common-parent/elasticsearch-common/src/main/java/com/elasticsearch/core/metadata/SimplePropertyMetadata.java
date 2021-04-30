package com.elasticsearch.core.metadata;

import com.alibaba.fastjson.JSONObject;
import com.elasticsearch.util.FastBeanCopier;
import org.apache.commons.collections.MapUtils;

import java.beans.ConstructorProperties;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjs
 */
public class SimplePropertyMetadata implements PropertyMetadata {
    private DataType valueType;
    private String id;
    private String name;
    private String description;
    private Map<String, Object> expands;

    public static SimplePropertyMetadata of(String id, String name, DataType type) {
        SimplePropertyMetadata metadata = new SimplePropertyMetadata();
        metadata.setId(id);
        metadata.setName(name);
        metadata.setValueType(type);
        return metadata;
    }

    @Override
    public void fromJson(JSONObject json) {
        throw new UnsupportedOperationException();
    }


    @Override
    public DataType getValueType() {
        return this.valueType;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Map<String, Object> getExpands() {
        return this.expands;
    }

    public void setValueType(DataType valueType) {
        this.valueType = valueType;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setExpands(Map<String, Object> expands) {
        this.expands = expands;
    }

    public SimplePropertyMetadata() {
    }

    @ConstructorProperties({"valueType", "id", "name", "description", "expands"})
    private SimplePropertyMetadata(DataType valueType, String id, String name, String description, Map<String, Object> expands) {
        this.valueType = valueType;
        this.id = id;
        this.name = name;
        this.description = description;
        this.expands = expands;
    }

    public static SimplePropertyMetadata of(DataType valueType, String id, String name, String description, Map<String, Object> expands) {
        return new SimplePropertyMetadata(valueType, id, name, description, expands);
    }
}
