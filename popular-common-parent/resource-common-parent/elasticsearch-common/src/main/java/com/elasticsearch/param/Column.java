package com.elasticsearch.param;

import java.io.Serializable;

/**
 * @author wenjs
 */
public class Column implements Serializable { 
    /**
     * 字段名
     */
    private String name;

    private String type;

    public Column() {
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

}
