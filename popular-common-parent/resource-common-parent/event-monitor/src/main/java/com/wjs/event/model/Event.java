package com.wjs.event.model;

import com.wjs.event.annotition.EventParam;
import java.util.Map;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:53
 */
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
