package com.wjs.mqtt.model.property;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 读取设备信息-回复
 * @author wenjs
 */
@Getter
@Setter
public class ReadPropertyMessageReply {
    Map<String,Object> headers;
    String deviceId;
    String messageId;
    long timestamp; //时间戳(毫秒)
    boolean success;
    Map<String,Object> properties;//属性键值对
}
