package com.wjs.produce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableCaching
@SpringBootApplication
public class ModuleProduceApplication {

    public static void main(String[] args) {
         SpringApplication.run(ModuleProduceApplication.class, args);
    }


    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
