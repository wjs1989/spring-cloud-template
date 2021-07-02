package com.xinyue.kafka.config;

import com.isky.kafka.constant.Topics;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 集群中需要使用到的topic，都在这里统一初始化
 * @author wenjs
 */

//@Import(KafkaTopicSelector.class)
@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;
    @Value("${spring.kafka.producer.properties.sasl.mechanism}")
    String saslMechanism;
    @Value("${spring.kafka.producer.properties.security.protocol}")
    String securityProtocol;
    @Value("${spring.kafka.producer.properties.sasl.jaas.config}")
    String saslJaasConfig;

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        // 这里设置SASL连接
        configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        configs.put(SaslConfigs.SASL_MECHANISM, saslMechanism);
        configs.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);

        return new KafkaAdmin(configs);
    }


    //手动创建topic示例
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.204.125.254:9092,10.204.125.254:9093,10.204.125.254:9094");
        configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        configs.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        configs.put(SaslConfigs.SASL_JAAS_CONFIG,"org.apache.kafka.common.security.plain.PlainLoginModule required username=\"isky\" password=\"isky_abc_123_isky\";");

        AdminClient client = KafkaAdminClient.create(configs);
        NewTopic newTopic = new NewTopic("abc", 3, (short) 3);
        Collection<NewTopic> newTopicList = new ArrayList<>();
        newTopicList.add(newTopic);
        CreateTopicsResult topics = client.createTopics(newTopicList);

        System.out.println(topics);
        client.listTopics().names().get().forEach( s->System.out.println(s));
    }
}
