package com.wjs.event.model;


import java.util.List;

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public List<EventBasicSelect> getList() {
        return list;
    }

    public void setList(List<EventBasicSelect> list) {
        this.list = list;
    }
}
