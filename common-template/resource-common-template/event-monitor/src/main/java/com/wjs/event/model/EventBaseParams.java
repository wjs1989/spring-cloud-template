package com.wjs.event.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:52
 */
@Data
public class EventBaseParams<T> {
    private List<HashMap<String,Object>> events;
    private T params;
}
