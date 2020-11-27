package com.wjs.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

//@Component
public class ConsumerListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @KafkaListener(topics = {"topic-hello"},groupId = "2")
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("收到消息的key={},value={}: " ,record.key(),record.value().toString());



        // logger.info("GoodsId: {}",orderDto.getGoodsId());

    }
}
