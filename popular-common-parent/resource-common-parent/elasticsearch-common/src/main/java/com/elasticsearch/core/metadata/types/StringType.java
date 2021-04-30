package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.metadata.Converter;
import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.ValidateResult;

/**
 * @author wenjs
 */
public class StringType extends AbstractType<StringType> implements DataType, Converter<String> {

    public static final String ID = "string";
    public static final StringType GLOBAL = new StringType();

    public StringType() {
    }

    @Override
    public String getId() {
        return "string";
    }

    @Override
    public String getName() {
        return "字符串";
    }

    @Override
    public ValidateResult validate(Object value) {
        return ValidateResult.success(String.valueOf(value));
    }

    @Override
    public String format(Object value) {
        return String.valueOf(value);
    }

    @Override
    public String convert(Object value) {
        return value == null ? null : String.valueOf(value);
    }


}
