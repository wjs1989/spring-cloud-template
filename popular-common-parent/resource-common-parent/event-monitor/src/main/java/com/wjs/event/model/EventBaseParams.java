package com.wjs.event.model;


import java.util.HashMap;
import java.util.List;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:52
 */
public class EventBaseParams<T> {
    private List<HashMap<String,Object>> events;
    private T params;

    public List<HashMap<String, Object>> getEvents() {
        return events;
    }

    public void setEvents(List<HashMap<String, Object>> events) {
        this.events = events;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
