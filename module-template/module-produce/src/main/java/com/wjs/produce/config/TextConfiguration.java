package com.wjs.produce.config;

import com.wjs.produce.config.properties.XProperties;
import com.wjs.produce.model.X;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
//@EnableConfigurationProperties(XProperties.class)
public class TextConfiguration {


    @Bean
    public X x1(){
        return new X("x1");
    }
    @Bean
    public X x2(){
        return new X("x2");
    }
}
