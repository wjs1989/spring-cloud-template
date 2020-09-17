package com.wjs.model.util;

import com.alibaba.fastjson.JSONObject;
import com.wjs.model.constant.MessageEnum;
import com.wjs.model.exception.GlobalException;

import java.util.Map;

public class MapUtils extends org.apache.commons.collections.MapUtils {

    /**
     * map转bean
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static <T> T mapToObject(Map map, Class<T> beanClass) throws Exception {
        //org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        if (map == null) {
            return null;
        }

        return JSONObject.parseObject(JSONObject.toJSONString(map),beanClass);
    }
    /**
     * 对象转map
     */
    public static Map<String, Object> convertToMap(Object obj) {
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return convertToMapAbsolute(obj);
    }

    public static Map<String, Object> convertToMapAbsolute(Object obj) {
        try {
            Map<String, Object> returnMap = (Map) JSONObject.parse(JSONObject.toJSONString(obj));
            return returnMap;
        } catch (Exception e) {
            throw new GlobalException(MessageEnum.PARAMETER_FORMAT_EXCEPTION);
        }
    }
}
