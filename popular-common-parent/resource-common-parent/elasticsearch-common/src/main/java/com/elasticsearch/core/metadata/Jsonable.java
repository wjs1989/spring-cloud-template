package com.elasticsearch.core.metadata;

import com.alibaba.fastjson.JSONObject;
import com.elasticsearch.util.BeanUtils;
import com.elasticsearch.util.FastBeanCopier;

/**
 * @author wenjs
 */
public interface Jsonable {
    default JSONObject toJson() {
        return (JSONObject) BeanUtils.copyProperties(this, JSONObject::new);
    }

    default void fromJson(JSONObject json) {
        BeanUtils.copyProperties(json, this);
    }


}