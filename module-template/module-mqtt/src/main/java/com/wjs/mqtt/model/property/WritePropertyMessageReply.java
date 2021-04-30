package com.wjs.mqtt.model.property;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 设置设备信息-回复
 * @author wenjs
 */
@Getter
@Setter
public class WritePropertyMessageReply {
    Map<String,Object> headers;
    String deviceId;
    String messageId;
    long timestamp; //时间戳(毫秒)
    boolean success;
    Map<String,Object> properties; //回复被修改的属性最新值
}
