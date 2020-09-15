package com.wjs.produce;

import com.wjs.produce.config.AppConfig;
import com.wjs.produce.executor.BeatInfo;
import com.wjs.produce.executor.BeatService;
import com.wjs.produce.model.X;
import org.apache.coyote.OutputBuffer;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootTest
class ModuleProduceApplicationTests {

    @Test
    void contextLoads() {

        //   AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(AppConfig.class);


        //System.out.println(ClassLayout.parseClass(AppConfig.class).toPrintable());

        X x = new X();
        //  x.hashCode();
        synchronized (x) {
            System.out.println(ClassLayout.parseInstance(x).toPrintable());
        }


    }


    @Test
    void beatServiceTest() throws InterruptedException {
        BeatService beatService = new BeatService();

        BeatInfo beatInfo = new BeatInfo();
        beatInfo.setName("wenjs");
        beatService.bate(beatInfo);

        Thread.sleep(10000000);
    }

    @Test
    void executorTest(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    }


}
