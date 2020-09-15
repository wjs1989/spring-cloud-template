package com.wjs.produce.executor;

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
            System.out.println(Thread.currentThread().getName());
            System.out.println(beatInfo.getName());
            BeatService.this.es.schedule(BeatService.this.new BeatTask(this.beatInfo),5000,TimeUnit.MILLISECONDS);
        }
    }
}
