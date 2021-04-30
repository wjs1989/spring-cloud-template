package com.elasticsearch.util;

import com.elasticsearch.core.metadata.Converter;
import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.PropertyMetadata;
import com.elasticsearch.core.metadata.types.DateTimeType;
import com.elasticsearch.core.metadata.types.GeoPoint;
import com.elasticsearch.core.metadata.types.GeoType;
import com.elasticsearch.index.ElasticSearchIndexMetadata;
import com.elasticsearch.param.QueryParam;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjs
 */
public class ElasticSearchConverter {
    public static SearchSourceBuilder convertSearchSourceBuilder(QueryParam queryParam, ElasticSearchIndexMetadata metadata) {
        return QueryParamTranslator.convertSearchSourceBuilder(queryParam, metadata);
    }

    public static Map<String, Object> convertDataToElastic(Map<String, Object> data, List<PropertyMetadata> properties) {
        for (PropertyMetadata property : properties) {
            DataType type = property.getValueType();
            Object val = data.get(property.getId());
            if (val == null) {
                continue;
            }
            //处理地理位置类型
            if (type instanceof GeoType) {
                GeoPoint point = ((GeoType) type).convert(val);
                Map<String, Object> geoData = new HashMap<>();
                geoData.put("lat", point.getLat());
                geoData.put("lon", point.getLon());
                data.put(property.getId(), geoData);
            } else if (type instanceof DateTimeType) {
                Date date = ((DateTimeType) type).convert(val);
                data.put(property.getId(), date.getTime());
            } else if (type instanceof Converter) {
                data.put(property.getId(), ((Converter<?>) type).convert(val));
            }
        }
        return data;
    }

    public static Map<String, Object> convertDataFromElastic(Map<String, Object> data, List<PropertyMetadata> properties) {
        for (PropertyMetadata property : properties) {
            DataType type = property.getValueType();
            Object val = data.get(property.getId());
            if (val == null) {
                continue;
            }
            //处理地理位置类型
            if (type instanceof GeoType) {
                data.put(property.getId(), ((GeoType) type).convertToMap(val));
            } else if (type instanceof DateTimeType) {
                Date date = ((DateTimeType) type).convert(val);
                data.put(property.getId(), date);
            } else if (type instanceof Converter) {
                data.put(property.getId(), ((Converter<?>) type).convert(val));
            }
        }
        return data;
    }
}
