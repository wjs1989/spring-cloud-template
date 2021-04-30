package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.metadata.FormatSupport;
import com.elasticsearch.core.metadata.ValidateResult;

import java.util.HashMap;
import java.util.Map;
import com.elasticsearch.core.metadata.Converter;
import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.ValidateResult;
/**
 * @author wenjs
 */
public class GeoType  extends AbstractType<GeoType> implements DataType, FormatSupport, Converter<GeoPoint> {
    public static final String ID = "geoPoint";
    public static final GeoType GLOBAL = new GeoType();
    private String latProperty = "lat";
    private String lonProperty = "lon";

    public GeoType() {
    }

    public GeoType latProperty(String property) {
        this.latProperty = property;
        return this;
    }

    public GeoType lonProperty(String property) {
        this.lonProperty = property;
        return this;
    }

    public String getId() {
        return "geoPoint";
    }

    public String getName() {
        return "地理位置";
    }

    public Map<String, Object> convertToMap(Object value) {
        Map<String, Object> mapGeoPoint = new HashMap();
        GeoPoint point = this.convert(value);
        if (point != null) {
            mapGeoPoint.put("lat", point.getLat());
            mapGeoPoint.put("lon", point.getLon());
        }

        return mapGeoPoint;
    }

    public GeoPoint convert(Object value) {
        return GeoPoint.of(value);
    }

    public ValidateResult validate(Object value) {
        GeoPoint geoPoint = this.convert(value);
        return geoPoint == null ? ValidateResult.fail("不支持的Geo格式:" + value) : ValidateResult.success(geoPoint);
    }

    public String format(Object value) {
        GeoPoint geoPoint = this.convert(value);
        return String.valueOf(geoPoint);
    }

    public String getLatProperty() {
        return this.latProperty;
    }

    public String getLonProperty() {
        return this.lonProperty;
    }

    public void setLatProperty(String latProperty) {
        this.latProperty = latProperty;
    }

    public void setLonProperty(String lonProperty) {
        this.lonProperty = lonProperty;
    }
}
