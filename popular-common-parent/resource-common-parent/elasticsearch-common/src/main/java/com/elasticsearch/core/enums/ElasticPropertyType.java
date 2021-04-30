package com.elasticsearch.core.enums;

import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.types.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

/**
 * @author wenjs
 */
public enum ElasticPropertyType {
    TEXT("text", "text", StringType::new),
    BYTE("byte", "byte", () -> new IntType().min(Byte.MIN_VALUE).max(Byte.MAX_VALUE)),
    SHORT("short", "short", () -> new IntType().min(Short.MIN_VALUE).max(Short.MAX_VALUE)),
    INTEGER("int", "integer", IntType::new),
    LONG("long", "long", LongType::new),
    DATE("date", "date", DateTimeType::new),
    HALF_FLOAT("half_float", "half_float", FloatType::new),
    FLOAT("float", "float", FloatType::new),
    DOUBLE("double", "double", DoubleType::new),
    BOOLEAN("boolean", "boolean", BooleanType::new),
    OBJECT("object", "object", ObjectType::new),
    AUTO("auto", "auto", () -> null),
    NESTED("nested", "nested", ObjectType::new),
    IP("ip", "ip", LongType::new),
    ATTACHMENT("attachment", "attachment", FileType::new),
    KEYWORD("string", "keyword", StringType::new),
    GEO_POINT("geo_point", "geo_point", GeoType::new);


    private String text;

    private String value;

    private Supplier<DataType> typeBuilder;

    private ElasticPropertyType(String text, String value, Supplier<DataType> typeBuilder) {
        this.text = text;
        this.value = value;
        this.typeBuilder = typeBuilder;
    }

    public DataType getType() {
        return typeBuilder.get();
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }


    public static ElasticPropertyType of(Object value) {
        if (!StringUtils.isEmpty(value)) {
            for (ElasticPropertyType elasticPropertyType : ElasticPropertyType.values()) {
                if (elasticPropertyType.getValue().equals(value)) {
                    return elasticPropertyType;
                }
            }
        }
        return null;
    }

    public static ElasticPropertyType ofJava(Object value) throws Exception {
        if (!StringUtils.isEmpty(value)) {
            for (ElasticPropertyType elasticPropertyType : ElasticPropertyType.values()) {
                if (elasticPropertyType.getText().equals(value)) {
                    return elasticPropertyType;
                }
            }
        }
        throw new Exception("未找到数据类型为：" + value + "的枚举");
    }
}
