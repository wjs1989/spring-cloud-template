package com.wjs.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ModelSeataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelSeataApplication.class, args);
    }

}
