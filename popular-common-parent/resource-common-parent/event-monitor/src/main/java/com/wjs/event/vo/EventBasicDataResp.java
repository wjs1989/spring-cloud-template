package com.wjs.event.vo;


import java.util.List;

public class EventBasicDataResp {

    private String name;

    private String value;

    private String type;

    private List<EventBasicParamResp> list;


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

    public List<EventBasicParamResp> getList() {
        return list;
    }

    public void setList(List<EventBasicParamResp> list) {
        this.list = list;
    }
}
