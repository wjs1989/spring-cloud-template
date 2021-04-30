package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.metadata.Converter;
import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.FormatSupport;
import com.elasticsearch.core.metadata.ValidateResult;

/**
 * @author wenjs
 */
public class BooleanType extends AbstractType<BooleanType> implements DataType, FormatSupport, Converter<Boolean> {
    public static final String ID = "boolean";
    public static final BooleanType GLOBAL = new BooleanType();
    private String trueText = "是";
    private String falseText = "否";
    private String trueValue = "true";
    private String falseValue = "false";

    public BooleanType() {
    }

    public BooleanType trueText(String trueText) {
        this.trueText = trueText;
        return this;
    }

    public BooleanType falseText(String falseText) {
        this.falseText = falseText;
        return this;
    }

    public BooleanType trueValue(String trueValue) {
        this.trueValue = trueValue;
        return this;
    }

    public BooleanType falseValue(String falseValue) {
        this.falseText = falseValue;
        return this;
    }

    @Override
    public String getId() {
        return "boolean";
    }
    @Override
    public String getName() {
        return "布尔值";
    }
    @Override
    public Boolean convert(Object value) {
        if (value instanceof Boolean) {
            return (Boolean)value;
        } else {
            String stringVal = String.valueOf(value).trim();
            if (!stringVal.equals(this.trueValue) && !stringVal.equals(this.trueText)) {
                return !stringVal.equals(this.falseValue) && !stringVal.equals(this.falseText) ? null : false;
            } else {
                return true;
            }
        }
    }
    @Override
    public ValidateResult validate(Object value) {
        Boolean trueOrFalse = this.convert(value);
        return trueOrFalse == null ? ValidateResult.fail("不支持的值:" + value) : ValidateResult.success(trueOrFalse);
    }
    @Override
    public String format(Object value) {
        Boolean trueOrFalse = this.convert(value);
        if (Boolean.TRUE.equals(trueOrFalse)) {
            return this.trueText;
        } else {
            return Boolean.FALSE.equals(trueOrFalse) ? this.falseText : "未知:" + value;
        }
    }

    public String getTrueText() {
        return this.trueText;
    }

    public String getFalseText() {
        return this.falseText;
    }

    public String getTrueValue() {
        return this.trueValue;
    }

    public String getFalseValue() {
        return this.falseValue;
    }

    public void setTrueText(String trueText) {
        this.trueText = trueText;
    }

    public void setFalseText(String falseText) {
        this.falseText = falseText;
    }

    public void setTrueValue(String trueValue) {
        this.trueValue = trueValue;
    }

    public void setFalseValue(String falseValue) {
        this.falseValue = falseValue;
    }
}
