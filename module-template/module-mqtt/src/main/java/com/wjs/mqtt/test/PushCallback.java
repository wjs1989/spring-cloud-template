package com.wjs.mqtt.test;

import com.alibaba.fastjson.JSONObject;
import com.wjs.mqtt.model.event.EventMessage;
import com.wjs.mqtt.process.IProcess;
import com.wjs.mqtt.process.ProcessManage;
import jdk.internal.org.objectweb.asm.Handle;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wenjs
 */
public class PushCallback implements MqttCallback {

    MqttClient mqttClient;

    public PushCallback(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }


    @SneakyThrows
    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");

        mqttClient.connect();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        String payload = new String(message.getPayload(), "UTF-8");

        System.out.println("接收消息内容 : " + JSONObject.toJSONString(payload));

        IProcess iProcess = ProcessManage.processMap.get(topic);
        if(iProcess != null){
            iProcess.messageArrived(mqttClient,topic,message);
        }

        Map<String,String> data = new HashMap<>();
        data.put("address","中学");

        EventMessage ev = new EventMessage();
        ev.setEvent("dev_msg");
        ev.setData(data);
        ev.setTimestamp(System.currentTimeMillis());

        System.out.println("事件 : " + JSONObject.toJSONString(ev));
        MqttMessage em = new MqttMessage();
        em.setQos(1);
        em.setRetained(true);
        em.setPayload(JSONObject.toJSONString(ev).getBytes("UTF-8"));

         String ev1 = "/dev_msg";
        //  String ev1 = "/device/aaa/1/message/event/1";
        String[] etopic = {"/dev_msg","/fire_alarm","/fault_alarm"};
        mqttClient.publish(etopic[new Random().nextInt(3)], em);
    }
}
