package com.wjs.produce;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test1 {
    public void print(){
        System.out.println("----------------");
    }
    @Test
    void executorTest(){
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);

        scheduled.schedule(()->{
            System.out.println("-------------");
        },5, TimeUnit.SECONDS);

        scheduled.execute(new Test1()::print);
    }

    @Test
    void threadTest() {
        int a =0;
        Thread t1 = new Thread(()->{
            while (a>0){
                break;
            }
            System.out.println("t1------------");

        });





    }
}
