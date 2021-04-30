package com.elasticsearch.core.metadata.types;

import java.math.BigDecimal;

/**
 * @author wenjs
 */
public class FloatType extends NumberType<Float> {
    public static final String ID = "float";
    private Integer scale;
    public static final FloatType GLOBAL = new FloatType();

    public FloatType() {
    }

    @Override
    public Object format(Object value) {
        Number val = this.convertNumber(value);
        if (val == null) {
            return super.format(value);
        } else {
            int scale = this.scale == null ? 2 : this.scale;
            String scaled = (new BigDecimal(val.toString())).setScale(scale, 4).toString();
            return super.format(scaled);
        }
    }

    public FloatType scale(Integer scale) {
        this.scale = scale;
        return this;
    }

    @Override
    public Float convert(Object value) {
        return (Float)super.convertNumber(value, Number::floatValue);
    }

    public String getId() {
        return "float";
    }

    public String getName() {
        return "单精度浮点数";
    }

    public Integer getScale() {
        return this.scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }
}
