package com.elasticsearch.core.metadata.types;

/**
 * @author wenjs
 */
public class LongType  extends NumberType<Long> {
    public static final String ID = "long";
    public static final LongType GLOBAL = new LongType();

    public LongType() {
    }

    public String getId() {
        return "long";
    }

    public String getName() {
        return "长整型";
    }

    @Override
    public Long convert(Object value) {
        return (Long)super.convertNumber(value, Number::longValue);
    }
}
