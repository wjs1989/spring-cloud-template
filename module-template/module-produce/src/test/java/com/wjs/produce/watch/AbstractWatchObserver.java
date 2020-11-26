package com.wjs.produce.watch;

import com.wjs.produce.watch.model.FileModifyDto;

public abstract class AbstractWatchObserver {
    protected FileWatchSubject subject;
    public abstract void update(FileModifyDto fileInfo);
}
