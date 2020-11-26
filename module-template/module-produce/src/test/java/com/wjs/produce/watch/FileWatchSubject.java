package com.wjs.produce.watch;

import com.wjs.produce.watch.model.FileModifyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FileWacthService
 * @Description: TODO 文件监控观察者类
 * @author: jswen
 * @date: 2020年1月19日14:20:21
 */
@Component
public class FileWatchSubject {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileWatchSubject.class);
    private List<AbstractWatchObserver> observers = new ArrayList<AbstractWatchObserver>();

    //文件变化列表
    private List<FileModifyDto> watchList = new ArrayList<>();

    //0 空闲状态  1 通知状态
    private int state;

    public void attach(AbstractWatchObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        if(state == 0){
            state = 1;
            //每次连续的通知，只会开启一个线程 使用线程池管理
            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    do{
                        List<FileModifyDto> watchListTmp = new ArrayList<>();
                        synchronized(watchList){
                            watchListTmp.addAll(watchList);
                            watchList.clear();
                        }

                        if(!watchListTmp.isEmpty()){
                            for (FileModifyDto filemd : watchListTmp){
                                for (AbstractWatchObserver observer : observers) {
                                    observer.update(filemd);
                                }
                            }
                        }
                    }while (!watchList.isEmpty());
                    state = 0;
                }
            };

            new Thread(runnable).start();
        }
    }

    public void AddWatchList(FileModifyDto watch){
        synchronized(this.watchList){
            this.watchList.add(watch);
        }

        this.notifyAllObservers();
    }

}
