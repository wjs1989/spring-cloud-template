package com.wjs.mqtt.model.property;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 位置  ->  进阶  ->  最佳实践
 * 读取设备信息
 * @author wenjs
 */
@Getter
@Setter
public class ReadPropertyMessage {
    Map<String,Object> headers;
    String deviceId;
    String messageId;
    long timestamp; //时间戳(毫秒)
    List<String> properties;//可读取多个属性
}
