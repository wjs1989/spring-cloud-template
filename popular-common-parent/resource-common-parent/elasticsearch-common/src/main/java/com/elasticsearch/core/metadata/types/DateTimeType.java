package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.config.DateFormatter;
import com.elasticsearch.core.metadata.Converter;
import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.ValidateResult;
import com.elasticsearch.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wenjs
 */
public class DateTimeType extends AbstractType<DateTimeType> implements DataType, Converter<Date> {
    private static final Logger log = LoggerFactory.getLogger(DateTimeType.class);
    public static final String ID = "date";
    public static final String TIMESTAMP_FORMAT = "timestamp";
    public static final DateTimeType GLOBAL = new DateTimeType();
    private String format = "timestamp";
    private ZoneId zoneId = ZoneId.systemDefault();
    private DateTimeFormatter formatter;

    public DateTimeType() {
    }

    public DateTimeType timeZone(ZoneId zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public DateTimeType format(String format) {
        this.format = format;
        this.getFormatter();
        return this;
    }

    @Override
    public String getId() {
        return "date";
    }
    @Override
    public String getName() {
        return "时间";
    }

    protected DateTimeFormatter getFormatter() {
        if (this.formatter == null && !"timestamp".equals(this.format)) {
            this.formatter = DateTimeFormatter.ofPattern(this.format);
        }

        return this.formatter;
    }
    @Override
    public ValidateResult validate(Object value) {

        return (value = this.convert(value)) == null ?
                ValidateResult.fail("不是合法的时间格式") : ValidateResult.success(value);
    }
    @Override
    public String format(Object value) {
        try {
            if ("timestamp".equals(this.format)) {
                return String.valueOf(this.convert(value).getTime());
            } else {
                Date dateValue = this.convert(value);
                return dateValue == null ? "" : LocalDateTime.ofInstant(dateValue.toInstant(), this.zoneId).format(this.getFormatter());
            }
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
            return "";
        }
    }
    @Override
    public Date convert(Object value) {
        if (value instanceof Instant) {
            return Date.from((Instant)value);
        } else if (value instanceof LocalDateTime) {
            return Date.from(((LocalDateTime)value).atZone(this.zoneId).toInstant());
        } else if (value instanceof Date) {
            return (Date)value;
        } else if (value instanceof Number) {
            return new Date(((Number)value).longValue());
        } else if (value instanceof String) {
            if (StringUtils.isNumber(value)) {
                return new Date(Long.parseLong((String)value));
            } else {
                Date data = DateFormatter.fromString((String)value);
                if (data != null) {
                    return data;
                } else {
                    DateTimeFormatter formatter = this.getFormatter();
                    if (null == formatter) {
                        throw new IllegalArgumentException("unsupported date format:" + value);
                    } else {
                        return Date.from(LocalDateTime.parse((String)value, formatter).atZone(this.zoneId).toInstant());
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("can not format datetime :" + value);
        }
    }

    public String getFormat() {
        return this.format;
    }

    public ZoneId getZoneId() {
        return this.zoneId;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    static {
        DateFormatter.supportFormatter.add(new ISODateTimeFormatter());
    }


}
