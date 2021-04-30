package com.wjs.mqtt.process;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author wenjs
 */
public interface IProcess {
      void messageArrived(MqttClient mqttClient, String topic, MqttMessage message) throws Exception;
}
