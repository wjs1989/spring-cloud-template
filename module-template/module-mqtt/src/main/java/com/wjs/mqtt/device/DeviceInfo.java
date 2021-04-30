package com.wjs.mqtt.device;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author wenjs
 */
@Getter
@Setter
public class DeviceInfo {
    String deviceId;
    Map<String,Object> properties;//属性键值对

}
