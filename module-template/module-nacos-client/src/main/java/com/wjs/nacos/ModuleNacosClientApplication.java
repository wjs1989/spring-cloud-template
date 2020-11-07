package com.wjs.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class ModuleNacosClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleNacosClientApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){ return new RestTemplate();}

}
