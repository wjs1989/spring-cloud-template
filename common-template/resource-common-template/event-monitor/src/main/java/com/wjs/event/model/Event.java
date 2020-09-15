package com.wjs.event.model;

import com.wjs.event.annotition.EventParam;
import lombok.Data;

import java.util.Map;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:53
 */
@Data
public class Event {

    /**
     * 事件类型 PREEVENT 前置，POSTEVENT 后置
     */
    @EventParam("事件类型")
    private String type;

    @EventParam("事件名称")
    private String name;

    @EventParam("扩展字段")
    private Map<String,Object> extend;
}
