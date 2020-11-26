package com.wjs.produce.queue;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueueTest();
    }

    public static void ConcurrentLinkedQueueTest(){
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.add("1");
        concurrentLinkedQueue.add("2");

        Iterator<String> iterator = concurrentLinkedQueue.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
        concurrentLinkedQueue.poll();
        iterator = concurrentLinkedQueue.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
        System.out.println(concurrentLinkedQueue.size());
    }
}
