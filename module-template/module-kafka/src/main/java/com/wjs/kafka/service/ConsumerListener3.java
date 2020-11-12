package com.wjs.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener3 {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @KafkaListener(id = "2",topics = {"topic-hello"},groupId = "123")
    public void listen(ConsumerRecords<?, ?> records) {
        records.forEach(record->{
            logger.info("listen收到消息的partition={},key={},value={}: " ,record.partition(),record.key(),record.value().toString());
        });
       // logger.info("GoodsId: {}",orderDto.getGoodsId());

    }
    @KafkaListener(id = "1",topics = {"topic-hello"},groupId = "123" )
    public void listen1(ConsumerRecords<?, ?> records) {
        records.forEach(record->{
            logger.info("listen1收到消息的partition={},key={},value={}: " ,record.partition(),record.key(),record.value().toString());
        });


        // logger.info("GoodsId: {}",orderDto.getGoodsId());

    }
}
