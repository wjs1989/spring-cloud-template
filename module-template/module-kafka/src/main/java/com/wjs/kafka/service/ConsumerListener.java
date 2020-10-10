package com.wjs.kafka.service;

import com.alibaba.fastjson.JSONObject;
import com.wjs.kafka.dto.OrderDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = {"topictest"},groupId = "2")
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("收到消息的key={},value={}: " ,record.key(),record.value().toString());

       // logger.info("GoodsId: {}",orderDto.getGoodsId());

    }
}
