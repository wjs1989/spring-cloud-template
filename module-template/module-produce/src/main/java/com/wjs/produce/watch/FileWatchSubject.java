package com.wjs.produce.watch;

import com.wjs.produce.watch.model.FileModifyDto;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: FileWacthService
 * @Description: TODO 文件监控观察者类
 * @author: jswen
 * @date: 2020年1月19日14:20:21
 */
@Component
public class FileWatchSubject {
    private static final Logger logger = LoggerFactory.getLogger(FileWatchSubject.class);
    private List<AbstractWatchObserver> observers = new ArrayList<AbstractWatchObserver>();
    //文件变化列表
    private LinkedBlockingQueue<FileModifyDto> watchList = new LinkedBlockingQueue<>();

    public void attach(AbstractWatchObserver observer) {
        observers.add(observer);
    }

    @PostConstruct
    public void notifyAllObservers() {
        Runnable runnable = new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    FileModifyDto take = watchList.take();
                    if (take != null) {
                        for (AbstractWatchObserver observer : observers) {
                            observer.update(take);
                        }
                    }
                }
            }
        };

        new Thread(runnable).start();
    }

    public void AddWatchList(FileModifyDto watch) {
        watchList.add(watch);
    }

}
