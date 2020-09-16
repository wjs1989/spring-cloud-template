package com.wjs.event.vo;

import lombok.Data;

import java.util.List;

@Data
public class EventBasicDataResp {

    private String name;

    private String value;

    private String type;

    private List<EventBasicParamResp> list;

}
