package com.wjs.produce;

import java.util.concurrent.CountDownLatch;

public class VolatileTest {

    static int x, y;
    static int a, b;

    public static void main(String[] args) throws InterruptedException {

        int count = 0;
        while (true) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch cd= new CountDownLatch(1);
            Thread t1 = new Thread(() -> {
                try {
                    cd.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                try {
                    cd.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            cd.countDown();
            t2.join();
            t1.join();

            System.out.println(String.format("count=%s,x=%s,y=%s", count++, x, y));

            if(x==1 && y==1){
                return;
            }
        }
    }


}
