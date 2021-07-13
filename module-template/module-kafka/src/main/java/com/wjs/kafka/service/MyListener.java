package com.wjs.kafka.service;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.stereotype.Component;

/**
 * 现在kafka集群有3台主机，每个topic有3个分区3个副本，
 * 所以一个topic建立三个监听，每个监听自动对应一个分区。
 *
 * 也可以对每个监听指定监听的分区。
 * @author wenjs
 */
@Component
public class MyListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String groupId = "listengroupId11";
    private final String topic = "example";
    private final String clientId1 = "listen0";
    private final String clientId2 = "listen1";
    private final String clientId3 = "listen2";
    private final String errorHandler = "testListenerErrorHandler";

    @KafkaListener(id = clientId1, groupId = groupId, idIsGroup = false, topics = {topic}, errorHandler = errorHandler)
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("listen1----------------------------------"+record);

     //   KafkaMessage<MyListener> message = KafkaMessage.of(record.value());


    }

    // @KafkaListener(id = clientId2, groupId = groupId, idIsGroup = false, topics = {topic})
    // public void listen2(ConsumerRecord<String, String> record) {
    //     logger.info("listen2----------------------------------");
    //     logger.info("partition2: " + record.partition());
    //     logger.info("收到消息的key: " + record.key());
    //     logger.info("收到消息的value: " + record.value());
    // }
    //
    // //  @KafkaListener(id = clientId3, groupId = groupId, topicPartitions ={ @TopicPartition(topic = "topic1", partitions = {"0"})})
    // @KafkaListener(id = clientId3, groupId = groupId, idIsGroup = false, topics = {topic})
    // public void listen3(ConsumerRecord<String, String> record) {
    //     logger.info("listen3----------------------------------");
    //     logger.info("partition3: " + record.partition());
    //     logger.info("收到消息的key: " + record.key());
    //     logger.info("收到消息的value: " + record.value());
    // }

    /**
     * @KafkaListener 异常响应，参考clientId1
     * @return
     */
    @Bean("testListenerErrorHandler")
    public KafkaListenerErrorHandler errorHandler() {
        return (m, e) -> {
            //Message<?> m, ListenerExecutionFailedException e
            System.out.println(e.getMessage());
            return e.getMessage();
        };
    }
}
