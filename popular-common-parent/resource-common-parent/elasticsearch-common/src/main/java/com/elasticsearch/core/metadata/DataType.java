package com.elasticsearch.core.metadata;

import java.util.Map;

/**
 * @author wenjs
 */
public interface DataType  extends Metadata, FormatSupport {

    ValidateResult validate(Object var1);

    default String getType() {
        return this.getId();
    }

    default Map<String, Object> getExpands() {
        return null;
    }

}
