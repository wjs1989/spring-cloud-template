package com.xinyue.kafka.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.text.MessageFormat;

/**
 * @author wenjs
 */
@Component
public class KafkaSender {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, String message) {
        send(topic, null, message);
    }

    public void send(String topic, String key, String message) {
        try {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, key, message);
            send.addCallback(new DefaultListenableFutureCallback(topic, key, message));

        } catch (Exception e) {
            logger.error(MessageFormat.format("消息发送异常，topic={0},key={1},message={2}", topic, key, message), e);
        }
    }

    /**
     * 指定发送分区
     *
     * @param topic
     * @param partition
     * @param key
     * @param message
     */
    public void send(String topic, Integer partition, String key, String message) {
        try {
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, partition, key, message);
            send.addCallback(new DefaultListenableFutureCallback(topic, key, message));
        } catch (Exception e) {
            logger.error(MessageFormat.format("消息发送异常，topic={0},key={1},message={2}", topic, key, message), e);
        }
    }

    /**
     * 发送结果监听
     */
    public class DefaultListenableFutureCallback implements ListenableFutureCallback {
        private String topic;
        private String key;
        private String message;

        public DefaultListenableFutureCallback(String topic, String key, String message) {
            this.topic = topic;
            this.key = key;
            this.message = message;
        }

        @Override
        public void onSuccess(Object o) {
            if (logger.isDebugEnabled()) {
                logger.debug(MessageFormat.format("消息发送成功:{0}", o.toString()));
            }
        }

        @Override
        public void onFailure(Throwable t) {
            logger.error(MessageFormat.format("消息发送失败，topic={0},key={1},message={2}", topic, key, message), t);
        }
    }
}
