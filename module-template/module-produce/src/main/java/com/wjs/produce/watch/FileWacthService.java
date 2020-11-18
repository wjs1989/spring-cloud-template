package com.wjs.produce.watch;

import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import com.wjs.produce.watch.model.FileModifyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * @ClassName: FileWacthService
 * @Description: TODO 文件监控服务
 * 可监控多层子目录
 * @author: jswen
 * @date: 2020年1月16日16:45:12
 */
@Component
public class FileWacthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileWacthService.class);
    private final String rootPath = "D:/test";

    @Autowired
    private FileWatchSubject fileWatchSubject;

    @PostConstruct
    public void doWatch() {
      //  new FileWatchObserver(fileWatchSubject);
        this.doWatch(rootPath, 5);
    }

    /**
     * @param path
     * @param maxDepth
     * @Title: instantlyDoWatch
     * @Description: 立刻马上调用
     * @return: void
     */
    public void instantlyDoWatch(String path, int maxDepth) {
        this.doWatch(path, maxDepth);
    }

    /**
     * @param path
     * @Title: doWatch
     * @Description: 创建及时监控
     * @return: void
     */
    private void doWatch(String path, int maxDepth) {

        try {
            /**
             * 监听服务每次take()\poll()操作都会导致线程监控阻塞，每次操作文件可能需要长时间，如果监听目录下有其他事件发生，将会导致事件丢失。
             */
            Watcher watcher = new SimpleWatcher() {
                @Override
                public void onCreate(WatchEvent<?> event, Path currentPath) {
                }

                @Override
                public void onModify(WatchEvent<?> event, Path currentPath) {
                    String fileName = String.valueOf(event.context());
                    String path = currentPath.toString();
                    try {
                        FileModifyDto watch = new FileModifyDto(Constant.FILE_WATCH_EVENT_MODIFY, path, fileName);
                        fileWatchSubject.AddWatchList(watch);
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onDelete(WatchEvent<?> event, Path currentPath) {

                    String fileName = String.valueOf(event.context());
                    String path = currentPath.toString();
                    try {
                        FileModifyDto watch = new FileModifyDto(Constant.FILE_WATCH_EVENT_DELETE, path, fileName);
                        fileWatchSubject.AddWatchList(watch);
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onOverflow(WatchEvent<?> event, Path currentPath) {//事件丢失
                }
            };
            WatchMonitor monitor = WatchMonitor.createAll(path, new DelayWatcher(watcher, 0));
            // WatchMonitor monitor = WatchMonitor.createAll(rootPath,  watcher);
            monitor.setMaxDepth(maxDepth);
            monitor.start();
        } catch (Exception e) {
            LOGGER.error(FileWacthService.class.getName() + " Exception ={}", e);
        }
    }

}