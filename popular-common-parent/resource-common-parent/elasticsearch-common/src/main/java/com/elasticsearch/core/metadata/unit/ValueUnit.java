package com.elasticsearch.core.metadata.unit;

import com.elasticsearch.core.metadata.FormatSupport;
import com.elasticsearch.core.metadata.Metadata;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wenjs
 */
public interface ValueUnit extends Metadata, FormatSupport, Serializable {
    String getSymbol();

    default Map<String, Object> getExpands() {
        return null;
    }
}
