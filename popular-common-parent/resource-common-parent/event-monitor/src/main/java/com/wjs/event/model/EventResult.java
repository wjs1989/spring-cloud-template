package com.wjs.event.model;


/**
 * @author wenjs
 * @Description:
 * @date 2020/9/1 14:13
 */
public class EventResult {
    /**
     * 事件执行结果， EventData为所有事件结果的基础类，每个事件都有不同的结果dto，实现 EventData 即可
     */
    public EventData result;

    public EventResult() {
    }

    public EventResult(EventData result) {
        this.result = result;
    }

    public static EventResult build(EventData eventData){
      return new EventResult(eventData);
    }

    public EventData getResult() {
        return result;
    }

    public void setResult(EventData result) {
        this.result = result;
    }
}
