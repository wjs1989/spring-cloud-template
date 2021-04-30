package com.wjs.mqtt.device;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjs
 */
@Getter
@Setter
public class DeviceManage {
    public static DeviceManage deviceManage = new DeviceManage();

    private Map<String ,DeviceInfo> deviceInfo;
    private DeviceManage(){
        init();
    }


    private void init(){
        deviceInfo = new HashMap<>();

        DeviceInfo  deviceInfo1 = new DeviceInfo();
        deviceInfo1.setDeviceId("1");
        deviceInfo1.setProperties(new HashMap<>());
        deviceInfo1.getProperties().put("temperature",30);
        deviceInfo1.getProperties().put("name","温度计");
        deviceInfo1.getProperties().put("time","2019-12-12 10:15:12");

        deviceInfo.put(deviceInfo1.getDeviceId(),deviceInfo1);

        DeviceInfo  deviceInfo2 = new DeviceInfo();
        deviceInfo2.setDeviceId("2");
        deviceInfo2.setProperties(new HashMap<>());
        deviceInfo2.getProperties().put("t",30);
        deviceInfo2.getProperties().put("name","温度计");
        deviceInfo2.getProperties().put("time","2019-12-12 10:15:12");

        deviceInfo.put(deviceInfo2.getDeviceId(),deviceInfo2);

    }


public void update(String key){

    Map<String, Object> properties = DeviceManage.deviceManage.getDeviceInfo().get(key).getProperties();

    //模拟更新属性
    if(properties.get("temperature") != null) {
        properties.put("temperature", (Integer) properties.get("temperature") + 1);
    }
    if(properties.get("t") != null) {
        properties.put("t", (Integer) properties.get("t") + 1);
    }
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    properties.put("time",df.format(new Date()));

}


}
