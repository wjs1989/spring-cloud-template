package com.elasticsearch.core.metadata.types;

import java.math.BigDecimal;

/**
 * @author wenjs
 */
public class DoubleType extends NumberType<Double> {
    public static final String ID = "double";
    private Integer scale;
    public static final DoubleType GLOBAL = new DoubleType();

    public DoubleType() {
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

    public DoubleType scale(Integer scale) {
        this.scale = scale;
        return this;
    }

    @Override
    public Double convert(Object value) {
        return (Double)super.convertNumber(value, Number::doubleValue);
    }

    public String getId() {
        return "double";
    }

    public String getName() {
        return "双精度浮点数";
    }

    public Integer getScale() {
        return this.scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }
}
