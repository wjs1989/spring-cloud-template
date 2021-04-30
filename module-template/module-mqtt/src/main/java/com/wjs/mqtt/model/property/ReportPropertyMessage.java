package com.wjs.mqtt.model.property;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 报告属性
 * @author wenjs
 */
@Getter
@Setter
public class ReportPropertyMessage {
    Map<String,Object> headers;
    String deviceId;
    String messageId; //可为空
    long timestamp; //时间戳(毫秒)
    Map<String,Object> properties;
}
