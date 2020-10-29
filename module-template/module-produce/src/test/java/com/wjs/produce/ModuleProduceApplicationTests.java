package com.wjs.produce;

import com.wjs.produce.executor.BeatInfo;
import com.wjs.produce.executor.BeatService;
import com.wjs.produce.model.X;
import com.wjs.remote.feign.RemoteUserService;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootTest
class ModuleProduceApplicationTests {

    private RemoteUserService remoteUserService;

    @Test
    void contextLoads() {

    }

}
