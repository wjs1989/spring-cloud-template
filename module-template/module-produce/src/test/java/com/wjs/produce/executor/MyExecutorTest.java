package com.wjs.produce.executor;

public class MyExecutorTest {

    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor(
                1, 5, 1000,1);

        for (int i = 0; i < 10; i++) {
            myExecutor.executor(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println("-----------------");
            });
        }

    }
}
