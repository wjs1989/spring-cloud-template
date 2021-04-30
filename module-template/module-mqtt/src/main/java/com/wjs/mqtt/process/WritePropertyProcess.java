package com.wjs.mqtt.process;

import com.alibaba.fastjson.JSONObject;
import com.wjs.mqtt.device.DeviceManage;
import com.wjs.mqtt.model.property.WritePropertyMessage;
import com.wjs.mqtt.model.property.WritePropertyMessageReply;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Map;

/**
 * @author wenjs
 */
public class WritePropertyProcess implements IProcess{
    @Override
    public void messageArrived(MqttClient mqttClient, String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload(), "UTF-8");
        WritePropertyMessage read = JSONObject.parseObject(payload, WritePropertyMessage.class);

        WritePropertyMessageReply reply = new WritePropertyMessageReply();
        reply.setHeaders(read.getHeaders());
        reply.setMessageId(read.getMessageId());
        reply.setDeviceId(read.getDeviceId());
        reply.setProperties(read.getProperties());
        reply.setSuccess(true);
        reply.setTimestamp(System.currentTimeMillis());

        Map<String, Object> properties = DeviceManage.deviceManage.getDeviceInfo().get(read.getDeviceId()).getProperties();
        read.getProperties().keySet().forEach(p -> {
            properties.put(p,read.getProperties().get(p));
        });

        String r = JSONObject.toJSONString(reply);
        MqttMessage mm = new MqttMessage();
        mm.setQos(1);
        mm.setRetained(true);
        mm.setPayload(r.getBytes("UTF-8"));
        System.out.println("返回消息内容 : " + r);
        mqttClient.publish(topic, mm);
    }
}
