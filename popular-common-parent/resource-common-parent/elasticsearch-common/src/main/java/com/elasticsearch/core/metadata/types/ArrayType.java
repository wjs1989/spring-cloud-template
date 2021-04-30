package com.elasticsearch.core.metadata.types;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.elasticsearch.core.metadata.Converter;
import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.ValidateResult;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wenjs
 */
public class ArrayType extends AbstractType<ArrayType> implements DataType, Converter<List<Object>> {

    public static final String ID = "array";
    private DataType elementType;

    public ArrayType() {
    }

    @Override
    public String getId() {
        return "array";
    }

    @Override
    public String getName() {
        return "数组";
    }

    public ArrayType elementType(DataType elementType) {
        this.elementType = elementType;
        return this;
    }

    @Override
    public ValidateResult validate(Object value) {
        List<Object> listValue = this.convert(value);
        if (this.elementType != null && value instanceof Collection) {
            for (Object v : listValue) {
                ValidateResult result = this.elementType.validate(v);
                if (!result.isSuccess()) {
                    return result;
                }
            }
        }

        return ValidateResult.success(listValue);
    }

    @Override
    public Object format(Object value) {
        if (this.elementType != null && value instanceof Collection) {
            Collection<?> collection = (Collection) value;
            return new JSONArray((List) collection.stream().map((data) -> {
                return this.elementType.format(data);
            }).collect(Collectors.toList()));
        } else {
            return JSON.toJSON(value);
        }
    }

    @Override
    public List<Object> convert(Object value) {
        if (value instanceof Collection) {
            return (List) ((Collection) value).stream().map((val) -> {
                return this.elementType instanceof Converter ? ((Converter) this.elementType).convert(val) : val;
            }).collect(Collectors.toList());
        } else {
            return (List) (value instanceof String ? JSON.parseArray(String.valueOf(value)) : Collections.singletonList(value));
        }
    }

    public DataType getElementType() {
        return this.elementType;
    }

    public void setElementType(DataType elementType) {
        this.elementType = elementType;
    }

}
