package com.elasticsearch.core.metadata;

import com.elasticsearch.core.metadata.unit.ValueUnit;

/**
 * @author wenjs
 */
public interface UnitSupported {
    ValueUnit getUnit();

    void setUnit(ValueUnit valueUnit);
}
