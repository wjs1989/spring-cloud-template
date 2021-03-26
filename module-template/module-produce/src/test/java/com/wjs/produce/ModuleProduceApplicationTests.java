package com.wjs.produce;

import com.wjs.produce.config.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class ModuleProduceApplicationTests {
    @Autowired
    RedisService redisService;

    @Test
    void contextLoads() {
        System.out.println(redisService.getString("a"));
        // ConcurrentHashMap
        // ;
        //
        // ThreadPoolExecutor
        // ;
        //
        // synchronized
    }


    public ModuleProduceApplicationTests(@Autowired BaseService c) {
    }


    public ModuleProduceApplicationTests(@Autowired BaseService c, @Autowired BaseService1 c1) {
    }


}
