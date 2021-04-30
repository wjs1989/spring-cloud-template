package com.wjs.mqtt.process;

import com.alibaba.fastjson.JSONObject;
import com.wjs.mqtt.device.DeviceManage;
import com.wjs.mqtt.model.event.EventMessage;
import com.wjs.mqtt.model.function.FunctionInvokeMessage;
import com.wjs.mqtt.model.function.FunctionInvokeMessageReply;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author wenjs
 */
public class InvokeFunctionProcess implements IProcess{

    public Map<String, Function<FunctionInvokeMessage,FunctionInvokeMessageReply>> invokeMap = new HashMap<>();

    public InvokeFunctionProcess(){
        init();
    }

    private void init(){
        invokeMap.put("start",this::addT);
        invokeMap.put("234",this::addT);
    }

    private FunctionInvokeMessageReply addT(FunctionInvokeMessage o) {

       Map<String, Object> properties = DeviceManage.deviceManage.getDeviceInfo().get(o.getDeviceId()).getProperties();

        o.getArgs().forEach(p -> {
            properties.put(p.getName(),p.getValue());
        });

        FunctionInvokeMessageReply reply = new FunctionInvokeMessageReply();
      //  reply.setHeaders(o.getHeaders());
        reply.setMessageId(o.getMessageId());
        reply.setDeviceId(o.getDeviceId());
        reply.setOutput(0);
        reply.setSuccess(true);
        reply.setFunctionId(o.getFunction());
        reply.setTimestamp(System.currentTimeMillis());

        return reply;
    }

    @Override
    public void messageArrived(MqttClient mqttClient, String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload(), "UTF-8");
        FunctionInvokeMessage read = JSONObject.parseObject(payload, FunctionInvokeMessage.class);

        FunctionInvokeMessageReply reply =   invokeMap.get(read.getFunction()).apply(read);

        String r = JSONObject.toJSONString(reply);
        MqttMessage mm = new MqttMessage();
        mm.setQos(1);
        mm.setRetained(true);
        mm.setPayload(r.getBytes("UTF-8"));

        System.out.println("返回消息内容 : " + r);
        mqttClient.publish(topic, mm);


    }


}
