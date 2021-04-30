package com.elasticsearch.core.metadata.types;

import com.elasticsearch.core.metadata.ValidateResult;
import com.elasticsearch.core.metadata.Converter;
import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.ValidateResult;
/**
 * @author wenjs
 */
public class GeoShapeType  extends AbstractType<GeoShapeType> implements Converter<GeoShape> {
    public static final String ID = "geoShape";
    public static final GeoShapeType GLOBAL = new GeoShapeType();

    public GeoShapeType() {
    }

    public ValidateResult validate(Object value) {
        GeoShape shape;
        return null == (shape = this.convert(value)) ? ValidateResult.builder().success(false).errorMsg("不支持的GepShape格式:" + value).build() : ValidateResult.success(shape);
    }

    public GeoShape convert(Object value) {
        return GeoShape.of(value);
    }

    public String getId() {
        return "geoShape";
    }

    public String getName() {
        return "地理地形";
    }

    public String getDescription() {
        return null;
    }

    public Object format(Object value) {
        return value;
    }
}
