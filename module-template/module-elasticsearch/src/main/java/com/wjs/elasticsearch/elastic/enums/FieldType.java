package com.wjs.elasticsearch.elastic.enums;

/**
 * @author wenjs
 */
public enum FieldType {

    TEXT("text"),

    KEYWORD("keyword"),

    INTEGER("integer"),

    DOUBLE("double"),

    DATE("date"),

    /**
     * 单条数据
     */
    OBJECT("object"),

    /**
     * 嵌套数组
     */
    NESTED("nested")

    ;


    FieldType(String type){
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

}
