package com.wjs.produce.queue;

import io.lettuce.core.resource.Delay;
import lombok.SneakyThrows;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class QueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueueTest();
    }

    public static void ConcurrentLinkedQueueTest() {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.add("1");
        concurrentLinkedQueue.add("2");

        Iterator<String> iterator = concurrentLinkedQueue.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        concurrentLinkedQueue.poll();
        iterator = concurrentLinkedQueue.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        System.out.println(concurrentLinkedQueue.size());
    }

    @SneakyThrows
    @Test
    public void delayQueue() {


        DelayQueue delayQueue = new DelayQueue();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(dateFormat.format(new Date()));

        // delayQueue.add(new MyDelayed("wenjs",5000));
        // delayQueue.add(new MyDelayed("wenjs",30000));
        // delayQueue.add(new MyDelayed("wenjs",3000));

        Thread thread = new Thread(new Runnable() {
            int index = 100000;
            @Override
            public void run() {
                while (true){
                    int time = new Random().nextInt(100000);
                    Date d = new Date( System.currentTimeMillis() +time);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    delayQueue.add(new MyDelayed("create ："+df.format(d),time));
                }
            }
        }) ;
         thread.start();

         int index = 0;
        while(true){
            Delayed take = delayQueue.take();
            System.out.println(index++ + " -> " +dateFormat.format(new Date()) + " -> " +take.toString() );
        }

    }


    public static class MyDelayed implements Delayed {
        private String name;
        private long start = System.currentTimeMillis();
        private long time;

        public MyDelayed(String name, long time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return String.format("{myDelayed:{name:%s,time:%s}}", name, time);
        }
    }

}
