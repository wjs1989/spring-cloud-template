package com.wjs.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wjs.elasticsearch.elastic.annotations.EsDocument;
import com.wjs.elasticsearch.elastic.annotations.EsField;
import com.wjs.elasticsearch.elastic.annotations.EsId;
import com.wjs.elasticsearch.elastic.enums.FieldType;

@EsDocument(index = "abc", type = "")
public class ElasticAct {
    /**
     * 主键标识，用户ES持久化
     */
    @EsId
    @EsField(type = FieldType.KEYWORD)
    private String id;

    @EsField(type = FieldType.KEYWORD)
    private String name;

    @EsField(type = FieldType.INTEGER)
    private Integer age;

    @EsField(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String birthday ;

    /**
     * JSON对象，实际存储数据
     */
    // private Map data;

}
