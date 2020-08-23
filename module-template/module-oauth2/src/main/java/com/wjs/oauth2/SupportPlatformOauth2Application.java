package com.wjs.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
/*
 * EnableResourceServer注解开启资源服务，因为程序需要对外暴露获取token的API和验证token的API所以该程序也是一个资源服务器
 * */
@EnableResourceServer
@EnableFeignClients
@SpringBootApplication
public class SupportPlatformOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SupportPlatformOauth2Application.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){ return new RestTemplate();}

}
