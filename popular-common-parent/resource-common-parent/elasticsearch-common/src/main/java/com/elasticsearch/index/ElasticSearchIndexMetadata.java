package com.elasticsearch.index;

import com.elasticsearch.core.metadata.PropertyMetadata;
import com.elasticsearch.util.ElasticSearchConverter;

import java.util.List;
import java.util.Map;

/**
 * @author wenjs
 */
public interface ElasticSearchIndexMetadata {

    String getIndex();

    List<PropertyMetadata> getProperties();

    PropertyMetadata getProperty(String property);

    default Map<String, Object> convertToElastic(Map<String, Object> map) {
        return ElasticSearchConverter.convertDataToElastic(map, getProperties());
    }

    default Map<String, Object> convertFromElastic(Map<String, Object> map) {
        return ElasticSearchConverter.convertDataFromElastic(map, getProperties());
    }

    default ElasticSearchIndexMetadata newIndexName(String name) {
        return new DefaultElasticSearchIndexMetadata(name, getProperties());
    }
}
