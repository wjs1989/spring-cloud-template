package com.wjs.mqtt.model.property;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 设置设备信息
 * @author wenjs
 */
@Getter
@Setter
public class WritePropertyMessage {
    Map<String,Object> headers;
    String deviceId;
    String messageId;
    long timestamp; //时间戳(毫秒)
    Map<String,Object> properties;
}
