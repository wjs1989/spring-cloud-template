package com.wjs.produce.config;

import com.wjs.produce.model.X;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextConfiguration {

    @Bean
    public X x(){
        return new X();
    }
}
