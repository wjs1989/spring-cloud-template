package com.elasticsearch.core.metadata.types;

import com.alibaba.fastjson.JSON;
import org.springframework.lang.Nullable;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * @author wenjs
 */
public class GeoPoint implements Serializable {
    private static final long serialVersionUID = -6849794470754667710L;
    private double lon;
    private double lat;

    public static GeoPoint of(Object val) {
        if (val == null) {
            return null;
        } else {
            Object tmp = val;
            if (val instanceof GeoPoint) {
                return (GeoPoint)val;
            } else {
                if (val instanceof String) {
                    String strVal = String.valueOf(val);
                    if (strVal.startsWith("{")) {
                        val = JSON.parseObject(strVal);
                    } else if (strVal.startsWith("[")) {
                        val = JSON.parseArray(strVal);
                    } else {
                        val = strVal.split("[,]");
                    }
                }

                if (val instanceof Map) {
                    Map<Object, Object> mapVal = (Map)val;
                    Object lon = mapVal.getOrDefault("lon", mapVal.get("x"));
                    Object lat = mapVal.getOrDefault("lat", mapVal.get("y"));
                    val = new Object[]{lon, lat};
                }

                if (val instanceof Collection) {
                    val = ((Collection)val).toArray();
                }

                if (val instanceof Object[]) {
                    Object[] arr = (Object[])((Object[])val);
                    if (arr.length >= 2) {
                        return new GeoPoint((new BigDecimal(String.valueOf(arr[0]))).doubleValue(), (new BigDecimal(String.valueOf(arr[1]))).doubleValue());
                    }
                }

                throw new IllegalArgumentException("unsupported geo format:" + tmp);
            }
        }
    }

    @Override
    public int hashCode() {
        int result = 1;
        long temp = Double.doubleToLongBits(this.lat);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.lon);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        return result;
    }
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof GeoPoint)) {
            return false;
        } else {
            GeoPoint other = (GeoPoint)obj;
            return Double.doubleToLongBits(this.lon) == Double.doubleToLongBits(other.lon) && Double.doubleToLongBits(this.lat) == Double.doubleToLongBits(other.lat);
        }
    }
    @Override
    public String toString() {
        return this.lon + "," + this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @ConstructorProperties({"lon", "lat"})
    public GeoPoint(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public GeoPoint() {
    }
}
