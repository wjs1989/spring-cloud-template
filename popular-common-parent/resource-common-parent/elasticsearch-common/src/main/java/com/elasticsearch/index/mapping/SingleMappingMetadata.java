package com.elasticsearch.index.mapping;

import com.elasticsearch.core.enums.ElasticDateFormat;
import com.elasticsearch.core.enums.ElasticPropertyType;

/**
 * @author bsetfeng
 * @since 1.0
 **/

public class SingleMappingMetadata {

    private String name;

    private ElasticDateFormat format;

    private ElasticPropertyType type;

    public SingleMappingMetadata(String name, ElasticDateFormat format, ElasticPropertyType type) {
        this.name = name;
        this.format = format;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElasticDateFormat getFormat() {
        return format;
    }

    public void setFormat(ElasticDateFormat format) {
        this.format = format;
    }

    public ElasticPropertyType getType() {
        return type;
    }

    public void setType(ElasticPropertyType type) {
        this.type = type;
    }
}
