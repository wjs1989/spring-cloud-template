package com.wjs.produce.watch;

import com.wjs.produce.watch.model.FileModifyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 观察者
 */
@Component
public class FileWatchObserver extends AbstractWatchObserver {
    private static final Logger logger = LoggerFactory.getLogger(FileWatchObserver.class);

    @Autowired
    public FileWatchObserver(FileWatchSubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update(FileModifyDto fileInfo) {
        if (fileInfo == null) {
            return;
        }

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s：%s-> %s",
                    fileInfo.getFlag(), fileInfo.getPath(), fileInfo.getFileName()));
        }

        try {
            if (Constant.FILE_WATCH_EVENT_MODIFY.equals(fileInfo.getFlag())) {
                System.out.println(fileInfo.getFlag() + ":" + fileInfo.getFileName());
            } else if (Constant.FILE_WATCH_EVENT_DELETE.equals(fileInfo.getFlag())) {
                System.out.println(fileInfo.getFlag() + ":" + fileInfo.getFileName());
            }
        } catch (Exception e) {
        }
    }

}
