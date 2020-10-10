package com.wjs.produce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

//@EnableCaching
@SpringBootApplication
public class ModuleProduceApplication {

    public static void main(String[] args) {
         SpringApplication.run(ModuleProduceApplication.class, args);
    }

}
