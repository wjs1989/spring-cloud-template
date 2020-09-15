package com.wjs.event;

import com.wjs.event.annotition.EventTypeSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wenjs
 * @Description: 执行事件的工厂类
 * @date 2020/8/31 17:10
 */
@Component
public class EventHandlerFactory {

    private Map<String, com.wjs.event.BaseEventHandler> eventHandlerCache;

    @Autowired
    public void init(List<com.wjs.event.BaseEventHandler> handlers) {
        eventHandlerCache = new HashMap<>();
        if (handlers != null && !handlers.isEmpty()) {
            for (com.wjs.event.BaseEventHandler handler : handlers) {
                EventTypeSign annotation = handler.getClass().getAnnotation(EventTypeSign.class);
                if (annotation != null) {
                    eventHandlerCache.put(annotation.value(), handler);
                }
            }
        }
    }

    public com.wjs.event.BaseEventHandler getEventHandler(String eventName) {
        Objects.requireNonNull(eventHandlerCache, "事件处理器未初始化");
        com.wjs.event.BaseEventHandler instance = eventHandlerCache.get(eventName);

        Objects.requireNonNull(instance, "未知的事件类型：" + eventName);
        return instance;
    }

    public List<com.wjs.event.BaseEventHandler> getAllBaseEventHandler() {
        if (eventHandlerCache == null || eventHandlerCache.isEmpty()) {
            return null;
        }
        return new ArrayList<>(eventHandlerCache.values()) ;
    }
}
