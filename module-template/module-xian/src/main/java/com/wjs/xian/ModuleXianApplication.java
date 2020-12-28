package com.wjs.xian;

import com.wjs.xian.config.FeignRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@SpringBootApplication
public class ModuleXianApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleXianApplication.class, args);
    }

    @Autowired
    private FeignRemote feignRemote;
    //
}
