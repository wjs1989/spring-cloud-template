package com.wjs.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;


public class MyListenerAck {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = {"topictestAck"},groupId = "1",containerFactory = "factoryAck")
    public void listen(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        try {
            logger.info("自行确认方式收到消息的key: " + record.key());
            logger.info("自行确认方式收到消息的value: " + record.value().toString());
        } finally {
            logger.info("消息确认！");
            ack.acknowledge();
        }
    }
}
