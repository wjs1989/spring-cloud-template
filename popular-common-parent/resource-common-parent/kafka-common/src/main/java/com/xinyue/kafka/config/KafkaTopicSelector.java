package com.xinyue.kafka.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wenjs
 */
public class KafkaTopicSelector implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(NewTopic.class);
        //
        // definition.addPropertyValue("name", this.getUrl(attributes));
        // definition.addPropertyValue("numPartitions", 3);
        // definition.addPropertyValue("replicationFactor", 3);
        // registry.registerBeanDefinition(NewTopic.class.getName(),definition.getBeanDefinition());
    }


}
