package com.wjs.mqtt.model.event;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author wenjs
 */
@Getter
@Setter
public class EventMessage {
    Map<String,Object> headers;
    String event; //事件标识,在元数据中定义
    Object data;  //与元数据中定义的类型一致,如果是对象类型,请转为java.util.HashMap,禁止使用自定义类型.
    long timestamp; //时间戳(毫秒)
}
