package com.wjs.produce.executor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class BeatService {
    ScheduledExecutorService es = null;

    public BeatService(){
        es = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName("com.wjs.produce.executor.BateService.sender");
                return thread;
            }
        });
    }

    public void bate(BeatInfo info){

        es.schedule(new BeatTask(info),5000,TimeUnit.MILLISECONDS);
    }


     class BeatTask implements Runnable{
        BeatInfo beatInfo;

        public BeatTask(BeatInfo beatInfo) {
            this.beatInfo = beatInfo;
        }
        @Override
        public void run() {
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+
                     "->"+  Thread.currentThread().getName()+":"+beatInfo.getName());
            BeatService.this.es.schedule(BeatService.this.new BeatTask(this.beatInfo),5000,TimeUnit.MILLISECONDS);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        BeatService beatService = new BeatService();
        BeatInfo beatInfo = new BeatInfo();
        beatInfo.setName("module-kafka");

        beatService.bate(beatInfo);

        Thread.sleep(100000);
    }
}
