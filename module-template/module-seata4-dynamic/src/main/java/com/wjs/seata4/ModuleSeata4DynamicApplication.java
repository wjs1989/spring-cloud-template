package com.wjs.seata4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ModuleSeata4DynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleSeata4DynamicApplication.class, args);
    }

}
