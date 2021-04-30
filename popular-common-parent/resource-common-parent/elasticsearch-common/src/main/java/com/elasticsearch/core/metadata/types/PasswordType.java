package com.elasticsearch.core.metadata.types;


import com.elasticsearch.core.metadata.Converter;
import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.ValidateResult;
/**
 * @author wenjs
 */
public class PasswordType  extends AbstractType<PasswordType> implements DataType, Converter<String> {
    public static final String ID = "password";
    public static final PasswordType GLOBAL = new PasswordType();

    public PasswordType() {
    }

    public String getId() {
        return "password";
    }

    public String getName() {
        return "密码";
    }

    public ValidateResult validate(Object value) {
        return ValidateResult.success(String.valueOf(value));
    }

    public String format(Object value) {
        return String.valueOf(value);
    }

    public String convert(Object value) {
        return value == null ? null : String.valueOf(value);
    }
}
