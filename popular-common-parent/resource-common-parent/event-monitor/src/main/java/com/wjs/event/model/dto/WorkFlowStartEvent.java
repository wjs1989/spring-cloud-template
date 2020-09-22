package com.wjs.event.model.dto;

import com.wjs.event.annotition.EventParam;
import com.wjs.event.constant.EventConstant;
import com.wjs.event.model.Event;

/**
 * @author wenjs
 * @Description: 工作流启动事件模型
 * @date 2020/9/1 10:47
 */
//@EventParams({
//        @EventParam(key="extend",value = "扩展字段",type = EventConstant.EVENT_PARAMS_FIELD_TYPE_EXTEND)
//})
public class WorkFlowStartEvent extends Event {

    @EventParam(value="业务key",type = EventConstant.EVENT_PARAMS_FIELD_TYPE_BANDING)
    private String businessKey;

    @EventParam(value="流程定义key",type = EventConstant.EVENT_PARAMS_FIELD_TYPE_SELECT)
    private String processDefinitionKey;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }
}
