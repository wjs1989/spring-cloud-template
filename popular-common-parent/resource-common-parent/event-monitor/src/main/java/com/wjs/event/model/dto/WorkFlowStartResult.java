package com.wjs.event.model.dto;

import com.wjs.event.model.EventData;

/**
 * @author wenjs
 * @Description:
 * @date 2020/9/2 13:51
 */
public class WorkFlowStartResult extends EventData {

    /**
     * 业务key
     */
    private String businessKey;

    /**
     * 工作流key
     */
    private String processDefinitionKey;

    /**
     * 流程实例id
     */
    private String processInstanceId;

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

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
