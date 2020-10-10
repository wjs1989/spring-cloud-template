package com.wjs.produce;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {
    private static boolean stop;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
                print();
            }

            System.out.println("结束");
        }, "t1");

        thread.start();

        Thread.sleep(1000);
        stop = true;

    }

    private static void threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10,
                1L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10)) {

            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("beforeExecute->" + Thread.currentThread().getName());
            }
        };

        //threadPoolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println("threadName->" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void fixed() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            System.out.println(111);
        });
    }

    public static void print() {
        // System.out.println("----------------");

//        synchronized (Test1.class){
//            int i = 0;
//        }


    }

//    void executorTest() {
//        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
//
//        scheduled.schedule(() -> {
//            System.out.println("-------------");
//        }, 5, TimeUnit.SECONDS);
//
//        scheduled.execute(new Test1()::print);
//    }
}
