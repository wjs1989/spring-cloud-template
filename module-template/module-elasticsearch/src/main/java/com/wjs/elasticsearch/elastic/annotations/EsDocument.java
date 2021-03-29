package com.wjs.elasticsearch.elastic.annotations;

import java.lang.annotation.*;

/**
 *  文档注解，用于做索引实体映射
 *  作用在类上
 * @author wenjs
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface EsDocument {

    /**
     * index : 索引名称
     * @return
     */
    String index();

    /**
     * 类型名称
     * @return
     */
    String type();
}
