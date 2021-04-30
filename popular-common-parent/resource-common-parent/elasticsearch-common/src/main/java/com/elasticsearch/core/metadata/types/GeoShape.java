package com.elasticsearch.core.metadata.types;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.*;

/**
 * @author wenjs
 */
public class GeoShape implements Serializable {
    private GeoShape.Type type;
    private List<Object> coordinates;

    public GeoShape() {
    }

    public static GeoShape of(Object value) {
        if (value instanceof GeoShape) {
            return (GeoShape)value;
        } else if (value instanceof GeoPoint) {
            return fromPoint((GeoPoint)value);
        } else {
            if (value instanceof String && ((String)value).startsWith("{")) {
                value = JSON.parseObject(String.valueOf(value));
            }

            if (value instanceof Map) {
                return of((Map)value);
            } else {
                throw new IllegalArgumentException("unsupported GeoShape:" + value);
            }
        }
    }

    public static GeoShape of(Map<String, Object> map) {
        GeoShape shape = new GeoShape();
        shape.type = GeoShape.Type.of(map.get("type"));
        shape.coordinates = shape.type.parseCoordinates(map.get("coordinates"));
        return shape;
    }

    public static GeoShape fromPoint(GeoPoint point) {
        GeoShape shape = new GeoShape();
        shape.type = GeoShape.Type.Point;
        shape.coordinates = new ArrayList(Arrays.asList(point.getLon(), point.getLat()));
        return shape;
    }

    public GeoShape.Type getType() {
        return this.type;
    }

    public List<Object> getCoordinates() {
        return this.coordinates;
    }

    public void setType(GeoShape.Type type) {
        this.type = type;
    }

    public void setCoordinates(List<Object> coordinates) {
        this.coordinates = coordinates;
    }

    public static enum Type {
        Point,
        MultiPoint,
        LineString,
        MultiLineString,
        Polygon,
        MultiPolygon,
        GeometryCollection;

        private Type() {
        }

        public static GeoShape.Type of(Object val) {
            GeoShape.Type[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                GeoShape.Type value = var1[var3];
                if (value.name().equalsIgnoreCase(String.valueOf(val))) {
                    return value;
                }
            }

            throw new IllegalArgumentException("unsupported GeoShape type:" + val);
        }

        public List<Object> parseCoordinates(Object coordinates) {
            if (coordinates instanceof Collection) {
                return new ArrayList((Collection)coordinates);
            } else if (coordinates instanceof String) {
                return (List)(((String)coordinates).startsWith("[") ? JSON.parseArray(String.valueOf(coordinates)) : new ArrayList(Arrays.asList(((String)coordinates).split(","))));
            } else {
                throw new IllegalArgumentException("unsupported coordinates type :" + coordinates);
            }
        }
    }
}
