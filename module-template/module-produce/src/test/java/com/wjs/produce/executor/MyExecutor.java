package com.wjs.produce.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池执行器
 */
public class MyExecutor {

    private BlockingQueue<Runnable> queue;
    private int corePoolSize = 0;
    private int maximumPoolSize = 0;
    private int keepAliveTime = 0;
    private ThreadFactory threadFactory;
    private List<Worker> workers;

    public MyExecutor(int corePoolSize, int maximumPoolSize, int keepAliveTime, int queueSize) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.queue = new LinkedBlockingQueue<>(queueSize);
        this.workers = new ArrayList<>();
        this.threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("com.wjs.produce.executor.MyExecutor");
                return thread;
            }
        };
    }

    /**
     * 新增执行任务
     *
     * @param runnable
     */
    public void executor(Runnable runnable) {
        if (this.workers.size() < corePoolSize) {
            addWorker(runnable);
        } else if (!queue.offer(runnable)) {
            if (this.workers.size() < maximumPoolSize) {
                addWorker(runnable);
            } else if (!queue.offer(runnable)) {
                reject(runnable);
            }
        }
    }

    private ThreadFactory getThreadFactory() {
        return this.threadFactory;
    }

    private Runnable getTask() throws InterruptedException {
        boolean timed = false;
        synchronized (this.workers) {
            timed = this.workers.size() > corePoolSize;
        }
        return timed ? this.queue.poll(keepAliveTime, TimeUnit.MILLISECONDS) : this.queue.take();
    }

    private void addWorker(Runnable runable){
        Worker worker = new Worker(runable);
        worker.start();
        workers.add(worker);
    }

    /**
     * 执行拒绝策略
     *
     * @param runnable
     */
    private void reject(Runnable runnable) {
        RejectedPolicy rejectPolicy = (r, executor) -> {
            //1、不处理，空方法
            //2、直接出异常
            //3、执行 r.run();
            //4、抛弃队列头数据，然后重新执行 。 executor.getQueue().poll(); executor.execute(r);

            System.out.println("--reject--");
        };

        rejectPolicy.rejectedExecution(runnable, this);
    }

    /**
     * 线程执行类
     */
    private class Worker implements Runnable {
        Thread thread;
        Runnable firstRunnable;

        public Worker(Runnable runnable) {
            this.firstRunnable = runnable;
            this.thread = getThreadFactory().newThread(this);
        }

        public void start() {
            if (this.thread != null) {
                this.thread.start();
            }
        }

        @Override
        public void run() {
            Runnable runnable = firstRunnable;
            try {
                while (runnable != null || (runnable = getTask()) != null) {
                    runnable.run();
                    runnable = null;
                }

                /**
                 * 跳出循环，则说明当前worker生命结束了
                 */
                synchronized (workers) {
                    workers.remove(this);
                    if (workers.size() < corePoolSize) {
                        addWorker(null);
                    }
                    System.out.println(workers.size());
                }
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * 拒绝策略
     */
    interface RejectedPolicy {
        void rejectedExecution(Runnable r, MyExecutor executor);
    }
}
