package com.elasticsearch.core.metadata;

/**
 * @author wenjs
 */
public interface Converter<T> {
    T convert(Object var1);
}
