package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.metadata.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import com.elasticsearch.core.metadata.ValidateResult;
import com.elasticsearch.core.metadata.unit.ValueUnit;

/**
 * @author wenjs
 */
public abstract class NumberType <N extends Number> extends AbstractType<NumberType<N>> implements UnitSupported, DataType, Converter<N> {
    private Number max;
    private Number min;
    private ValueUnit unit;

    public NumberType() {
    }

    public NumberType<N> unit(ValueUnit unit) {
        this.unit = unit;
        return this;
    }

    public NumberType<N> max(Number max) {
        this.max = max;
        return this;
    }

    public NumberType<N> min(Number min) {
        this.min = min;
        return this;
    }

    @Override
    public Object format(Object value) {
        if (value == null) {
            return null;
        } else {
            ValueUnit unit = this.getUnit();
            return unit == null ? value : unit.format(value);
        }
    }

    @Override
    public ValidateResult validate(Object value) {
        try {
            N numberValue = this.convert(value);
            if (numberValue == null) {
                return ValidateResult.fail("数字格式错误:" + value);
            } else if (this.max != null && numberValue.doubleValue() > this.max.doubleValue()) {
                return ValidateResult.fail("超过最大值:" + this.max);
            } else {
                return this.min != null && numberValue.doubleValue() < this.min.doubleValue() ? ValidateResult.fail("小于最小值:" + this.min) : ValidateResult.success(numberValue);
            }
        } catch (NumberFormatException ex) {
            return ValidateResult.fail(ex.getMessage());
        }
    }

    public N convertNumber(Object value, Function<Number, N> mapper) {
        return (N) Optional.ofNullable(this.convertNumber(value)).map(mapper).orElse((N) null);
    }

    public Number convertNumber(Object value) {
        if (value instanceof Number) {
            return (Number)value;
        } else if (value instanceof String) {
            try {
                return new BigDecimal((String)value);
            } catch (NumberFormatException ex) {
                return null;
            }
        } else {
            return value instanceof Date ? ((Date)value).getTime() : null;
        }
    }

    @Override
    public abstract N convert(Object var1);

    public long getMax(long defaultVal) {
        return (Long)Optional.ofNullable(this.getMax()).map(Number::longValue).orElse(defaultVal);
    }

    public long getMin(long defaultVal) {
        return (Long) Optional.ofNullable(this.getMin()).map(Number::longValue).orElse(defaultVal);
    }

    public double getMax(double defaultVal) {
        return (Double)Optional.ofNullable(this.getMax()).map(Number::doubleValue).orElse(defaultVal);
    }

    public double getMin(double defaultVal) {
        return (Double)Optional.ofNullable(this.getMin()).map(Number::doubleValue).orElse(defaultVal);
    }

    public Number getMax() {
        return this.max;
    }

    public Number getMin() {
        return this.min;
    }

    @Override
    public ValueUnit getUnit() {
        return this.unit;
    }

    public void setMax(Number max) {
        this.max = max;
    }

    public void setMin(Number min) {
        this.min = min;
    }

    @Override
    public void setUnit(ValueUnit unit) {
        this.unit = unit;
    }
}
