package com.wjs.kafka.config;

import com.wjs.kafka.service.MyListener;
import com.wjs.kafka.service.MyListenerAck;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;
//    @Value("${spring.kafka.consumer.session-timeout:}")
//    private String sessionTimeout;
    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private String autoCommitInterval;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka.consumer.concurrency}")
    private int concurrency;

    public Map<String, Object> consumerConfigs() {
        //System.setProperty("java.security.auth.login.config", "F:/kafka_client_jaas.conf");

        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
       // propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

<<<<<<< HEAD
        // 这里设置SASL连接
       propsMap.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        propsMap.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        propsMap.put(SaslConfigs.SASL_JAAS_CONFIG,
                "org.apache.kafka.common.security.plain.PlainLoginModule required  username=\"admin\"  password=\"admin\";");
=======

        propsMap.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        propsMap.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        propsMap.put(SaslConfigs.SASL_JAAS_CONFIG,
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"writer\" password=\"123456\";");

>>>>>>> 42c0a74295a3319939547addf2912bc9ffdbd9d2
        return propsMap;
    }

    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

   // @Bean
    public MyListener listener() {
        return new MyListener();
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency);
        factory.setBatchListener(true);
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }


    public Map<String, Object> consumerConfigsAck() {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return propsMap;
    }

    public ConsumerFactory<String, String> consumerFactoryAck() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigsAck());
    }

    //@Bean("listenerAck")
    public MyListenerAck listenerAck() {
        return new MyListenerAck();
    }

    @Bean("factoryAck")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaListenerContainerFactoryAck() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryAck());
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(1500);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }
}
