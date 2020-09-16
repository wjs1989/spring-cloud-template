package com.wjs.event.model;

import com.wjs.event.endpoint.EventBasicDataEndpoint;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjs
 * @Description: 事件数据资源管理
 * @date 2020/9/1 16:13
 */
public class EventDataManager {

    /**
     * 缓存事件执行结果
     */
    private static final ThreadLocal<Map<String,EventData>> eventData = new NamedThreadLocal("event data resources");

    /**
     * 缓存业务数据，保存table,column,type:value 。用在事件参数中绑定参数的解析
     */
    private static final ThreadLocal<Map<String,Object>> businessData = new NamedThreadLocal("business data resources");

    /**
     * 缓存 查询基础事件时，携带的参数信息，然后在事件响应体中取出使用。
     * 主要作用是防止不同事件基础数据需要不同的查询参数，而去修改已有的核心方法。
     * 可以参考
     * @see EventBasicDataEndpoint
     * 中的getEventBasicData方法
     */
    private static final ThreadLocal<Map<String,Object>> paramsData = new NamedThreadLocal("params data resources");

    public static EventData getEventData(String eventName){
        Map<String,EventData> map = eventData.get();
        if (map == null) {
            map = new HashMap();
            eventData.set(map);
        }
        return map.get(eventName);
    }

    public static void putEventData(String eventName,EventData event){
        Map<String,EventData> map = eventData.get();
        if (map == null) {
            map = new HashMap();
            eventData.set(map);
        }

        eventData.get().put(eventName,event);
    }

    public static Object getBusinessData(String businessName){
        Map<String,Object> map = businessData.get();
        if (map == null) {
            map = new HashMap();
            businessData.set(map);
        }
        return map.get(businessName);
    }

    public static void putBusinessData(String businessName,Object data){
        Map<String,Object> map = businessData.get();
        if (map == null) {
            map = new HashMap();
            businessData.set(map);
        }

        businessData.get().put(businessName,data);
    }

    public static Object getParamsData(String paramsName){
        Map<String,Object> map = paramsData.get();
        if (map == null) {
            map = new HashMap();
            paramsData.set(map);
        }
        return map.get(paramsName);
    }

    public static void putParamsData(String paramsName,Object data){
        Map<String,Object> map = paramsData.get();
        if (map == null) {
            map = new HashMap();
            paramsData.set(map);
        }

        paramsData.get().put(paramsName,data);
    }

}
