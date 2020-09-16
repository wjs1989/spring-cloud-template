package com.wjs.event.model;

import lombok.Data;

import java.util.List;

@Data
public class EventBasicParam {
    /**
     * 参数说明
     */
    private String name;

    /**
     * 参数名称
     */
    private String value;
    /**
     * 参数类型，select 选择类型，input 输入类型， banding 绑定类型（table,column,type）
     */
    private String type;

    /**
     * 是否必填 true false
     */
    private boolean request;

    /**
     * 事件select值域列表
     */
    private List<EventBasicSelect> list;
}
