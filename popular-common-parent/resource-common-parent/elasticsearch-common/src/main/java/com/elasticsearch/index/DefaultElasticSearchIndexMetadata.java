package com.elasticsearch.index;

import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.PropertyMetadata;
import com.elasticsearch.core.metadata.SimplePropertyMetadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjs
 */
public class DefaultElasticSearchIndexMetadata implements ElasticSearchIndexMetadata {
    private String index;

    private Map<String, PropertyMetadata> properties = new HashMap<>();

    public DefaultElasticSearchIndexMetadata(String index) {
        this.index = index.toLowerCase().trim();
    }

    public DefaultElasticSearchIndexMetadata(String index, List<PropertyMetadata> properties) {
        this(index);
        properties.forEach(this::addProperty);
    }

    @Override
    public PropertyMetadata getProperty(String property) {
        return properties.get(property);
    }

    @Override
    public String getIndex() {
        return index;
    }

    @Override
    public List<PropertyMetadata> getProperties() {
        return new ArrayList<>(properties.values());
    }

    public DefaultElasticSearchIndexMetadata addProperty(PropertyMetadata property) {
        properties.put(property.getId(), property);
        return this;
    }

    public DefaultElasticSearchIndexMetadata addProperty(String property, DataType type) {
        SimplePropertyMetadata metadata=new SimplePropertyMetadata();
        metadata.setValueType(type);
        metadata.setId(property);
        properties.put(property, metadata);
        return this;
    }
}
