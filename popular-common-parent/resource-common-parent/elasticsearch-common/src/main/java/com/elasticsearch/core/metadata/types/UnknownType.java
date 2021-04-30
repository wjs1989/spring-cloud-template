package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.ValidateResult;

/**
 * @author wenjs
 */
public class UnknownType  implements DataType {
    public UnknownType() {
    }

    public ValidateResult validate(Object value) {
        return ValidateResult.success();
    }

    public String getId() {
        return "unknown";
    }

    public String getName() {
        return "未知类型";
    }

    public String getDescription() {
        return "未知类型";
    }

    public String format(Object value) {
        return String.valueOf(value);
    }
}
