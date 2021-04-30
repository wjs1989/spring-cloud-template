package com.wjs.mqtt.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author wenjs
 */
public class ServerMQTT {
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://127.0.0.1:61613";
    //定义一个主题
    public static final String TOPIC1 = "/properties_read";
    public static final String TOPIC2 = "/aaa/2/properties/read";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server11";

    private MqttClient client;
    private MqttTopic topic1;
    private MqttTopic topic2;
    private String userName = "admin";
    private String passWord = "password";

    private MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public ServerMQTT() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback(client));
            client.connect(options);

            topic1 = client.getTopic(TOPIC1+"/p2p/1");
            topic2 = client.getTopic(TOPIC1+"/p2p/2");
            //topic2 = client.getTopic(TOPIC2);

            int[] Qos  = {1};
            String[] topic1 = {TOPIC1};
            client.subscribe(topic1, Qos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }

    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws MqttException {
        ServerMQTT server = new ServerMQTT();

        server.message = new MqttMessage();
        server.message.setQos(1);
        server.message.setRetained(true);
        String m = "eyJtZXNzYWdlSWQiOiIxMzg1NTA3MzkzMTU3MjQyODgwIiwiZGV2aWNlSWQiOiIxIiwicHJvcGVydGllcyI6WyJuYW1lIl19";
        server.message.setPayload(m.getBytes());
        server.publish(server.topic1 , server.message);
        System.out.println(server.message.isRetained() + "------ratained状态");
    }
}
