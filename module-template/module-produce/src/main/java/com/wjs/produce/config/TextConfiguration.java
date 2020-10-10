package com.wjs.produce.config;

import com.wjs.produce.config.properties.XProperties;
import com.wjs.produce.model.X;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
@EnableConfigurationProperties(XProperties.class)
public class TextConfiguration {

    @Bean
    public X x(XProperties xProperties){
        return new X(String.valueOf(xProperties.getDurationTime().toNanos()));
    }
}
