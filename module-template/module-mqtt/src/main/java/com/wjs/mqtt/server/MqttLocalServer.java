package com.wjs.mqtt.server;

import io.vertx.core.Vertx;
import io.vertx.mqtt.MqttServer;

/**
 * @author wenjs
 */
public class MqttLocalServer {


    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        MqttServer mqttServer = MqttServer.create(vertx);




    }


}
