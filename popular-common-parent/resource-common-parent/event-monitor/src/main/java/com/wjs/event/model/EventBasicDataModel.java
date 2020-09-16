package com.wjs.event.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenjs
 * @Description: 事件基础数据模型
 * @date 2020/9/1 16:17
 */
@Data
public class EventBasicDataModel {
    /**
     * 事件名称
     */
    private String name;

    /**
     * 事件key
     */
    private String value;

    /**
     * 事件类型：PREEVENT 前置事件；POSTEVENT 后置事件
     */
    private String type;

    /**
     * 参数列表
     */
    private List<EventBasicParam> list;

    public void addParam(EventBasicParam param){
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(param);
    }

}
