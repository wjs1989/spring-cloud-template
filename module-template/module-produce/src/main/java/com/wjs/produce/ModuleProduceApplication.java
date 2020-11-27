package com.wjs.produce;

import com.wjs.remote.UserRemoteAutoConfiguration;
import com.wjs.remote.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
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

    @Autowired
    private RemoteUserService remoteUserService;
}
