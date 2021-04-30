package com.wjs.mqtt.process;

import com.alibaba.fastjson.JSONObject;
import com.wjs.mqtt.device.DeviceManage;
import com.wjs.mqtt.model.property.ReadPropertyMessage;
import com.wjs.mqtt.model.property.ReadPropertyMessageReply;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjs
 */
public class ReadPropertyProcess implements IProcess{

    @Override
    public void messageArrived(MqttClient mqttClient,String topic, MqttMessage message) throws Exception {

        String payload = new String(message.getPayload(), "UTF-8");
        ReadPropertyMessage read = JSONObject.parseObject(payload, ReadPropertyMessage.class);
        ReadPropertyMessageReply reply = new ReadPropertyMessageReply();
        reply.setHeaders(read.getHeaders());
        reply.setMessageId(read.getMessageId());
        reply.setDeviceId(read.getDeviceId());
        reply.setProperties(new HashMap<>());
        reply.setSuccess(true);
        reply.setTimestamp(System.currentTimeMillis());

        Map<String, Object> properties = DeviceManage.deviceManage.getDeviceInfo().get(read.getDeviceId()).getProperties();

        read.getProperties().forEach(p -> {
            reply.getProperties().put(p, properties.get(p) );
        });

        DeviceManage.deviceManage.update(read.getDeviceId());
        String r = JSONObject.toJSONString(reply);
        MqttMessage mm = new MqttMessage();
        mm.setQos(1);
        mm.setRetained(true);
        mm.setPayload(r.getBytes("UTF-8"));

        System.out.println("返回消息内容 : " + r);

        mqttClient.publish(topic, mm);
    }
}
