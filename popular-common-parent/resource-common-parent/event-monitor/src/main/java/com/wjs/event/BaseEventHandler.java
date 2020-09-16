package com.wjs.event;

import com.wjs.event.model.EventBasicDataModel;
import com.wjs.event.model.EventResult;

import java.util.Map;

/**
 * @author wenjs
 * @Description: 事件执行的基础类
 * @date 2020/8/31 17:10
 */
public interface BaseEventHandler {

    /**
     * 事件的执行入口方法
     * @param event
     */
    EventResult exec(Map event);

    /**
     * 事件基础数据模型
     * @param projectId
     * @param pageId
     * @return
     */
    EventBasicDataModel getParams();
}
