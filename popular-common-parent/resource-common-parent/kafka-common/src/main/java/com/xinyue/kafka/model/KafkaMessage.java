package com.xinyue.kafka.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * @author wenjs
 */
public class KafkaMessage<T> {
    private Head head;
    private T body;

    public KafkaMessage() {
        head = new Head();
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public void setService(String service) {
        this.head.service = service;
    }

    public void setCommand(String command) {
        this.head.command = command;
    }

    public static class Head {
        private String service;
        private String command;

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static <R> KafkaMessage of(String message) {
        return JSONObject.parseObject(message, new TypeReference<KafkaMessage<R>>() {
        });
    }
}
