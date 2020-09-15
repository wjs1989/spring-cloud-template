package com.wjs.event.model.dto;

import com.wjs.event.model.EventData;
import lombok.Data;

/**
 * @author wenjs
 * @Description:
 * @date 2020/9/2 13:51
 */
@Data
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
}
