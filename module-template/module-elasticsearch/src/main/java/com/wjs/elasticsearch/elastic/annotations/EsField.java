package com.wjs.elasticsearch.elastic.annotations;

import com.wjs.elasticsearch.elastic.enums.AnalyzerType;
import com.wjs.elasticsearch.elastic.enums.FieldType;

import java.lang.annotation.*;

/**
 * 作用在字段上，用于定义类型，映射关系
 * @author wenjs
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface EsField {
    FieldType type() default FieldType.TEXT;

    /**
     * 指定分词器
     * @return
     */
    AnalyzerType analyzer() default AnalyzerType.STANDARD;
}
