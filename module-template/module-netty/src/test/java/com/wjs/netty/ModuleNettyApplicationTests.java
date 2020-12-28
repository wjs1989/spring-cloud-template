package com.wjs.netty;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//@SpringBootTest
class ModuleNettyApplicationTests {

    static Logger log = LoggerHelper.getInstance().getLogger(ModuleNettyApplicationTests.class);
    //static Logger log = LoggerFactory.getLogger(ModuleNettyApplicationTests.class);


    @Test
    void contextLoads() throws ExecutionException, InterruptedException, IOException {

//        FutureTask task = new FutureTask(()->{
//            int a = 10;
//            Thread.sleep(1000);
//            return a;
//        });
//
//        new Thread(task).start();

        for (int i = 0; i < 10000 ; i++) {
            log.info("异步打印日志" +  i);
        }

        System.out.println("==================================");

        System.out.println("==================================");

        System.in.read();
    }

}
