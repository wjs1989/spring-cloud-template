package com.wjs.produce;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class JsonTest {

    public static void main(String[] args) {

        //  ExecutorService executorService = Executors.newFixedThreadPool(1);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()) {

            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("------beforeExecute-------");
            }
            protected void afterExecute(Thread t, Runnable r) {
                System.out.println("------afterExecute-------");
            }
        };

        threadPoolExecutor.execute(()->{
            System.out.println("——————————————execute————————————");
        });

    }
}
