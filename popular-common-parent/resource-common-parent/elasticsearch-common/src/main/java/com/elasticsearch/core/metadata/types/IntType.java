package com.elasticsearch.core.metadata.types;

/**
 * @author wenjs
 */
public class IntType extends NumberType<Integer> {
    public static final String ID = "int";
    public static final IntType GLOBAL = new IntType();

    public IntType() {
    }

    public String getId() {
        return "int";
    }

    public String getName() {
        return "整型";
    }

    @Override
    public Integer convert(Object value) {
        return (Integer)super.convertNumber(value, Number::intValue);
    }
}
