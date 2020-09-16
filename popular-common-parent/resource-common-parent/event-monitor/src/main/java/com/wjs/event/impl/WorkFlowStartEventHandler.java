package com.wjs.event.impl;

import com.wjs.event.AbstractEventHandler;
import com.wjs.event.annotition.EventTypeSign;
import com.wjs.event.constant.EventConstant;
import com.wjs.event.model.*;
import com.wjs.event.model.dto.WorkFlowStartEvent;
import com.wjs.event.model.dto.WorkFlowStartResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 17:11
 */
@Slf4j
@EventTypeSign(name = EventConstant.WORK_FLOW_START_NAME, value = EventConstant.WORK_FLOW_START)
@Component
public class WorkFlowStartEventHandler extends AbstractEventHandler<WorkFlowStartEvent> {

   // @Autowired
  //  private WorkFlowRemoteService workFlowRemoteService;

    /**
     * 设置 select字段的参数值域，filed 为字段名称
     * @return
     */
    @Override
    protected List<EventBasicSelect> getSelect(String filed) {
      // System.out.println("projectId="+EventDataManager.getParamsData("projectId"));

        List<EventBasicSelect> selects = new ArrayList<>();
        for(int i =0;i<1;i++){
            EventBasicSelect select = new EventBasicSelect();
            select.setName("（虚拟）流程"+ i);
            select.setValue("workflow"+ i);
            selects.add(select);
        }
        return selects;
    }

    @Override
    protected EventResult preProcess(WorkFlowStartEvent event) {
        WorkFlowStartResult eventData = new WorkFlowStartResult();
        return EventResult.build(eventData);
    }

    @Override
    protected EventResult postProcess(WorkFlowStartEvent event) {

        WorkFlowStartResult eventData = new WorkFlowStartResult();
   //     WorkflowStartParams params = new WorkflowStartParams();

//        params.setUserId("userid");
//        params.setBusinessKey(event.getBusinessKey());
//        params.setProcessDefinitionKey(event.getProcessDefinitionKey());
//        params.setVariables(event.getExtend());
//        try {
//            BaseResult<WorkflowStartResponse> result = workFlowRemoteService.start(params);
//            if (result == null) {
//                log.info("远程调用工作流启动接口返回为空");
//            } else if (MessageEnum.SUCCESS.getKey().equals(result.getCode())) {
//                WorkflowStartResponse body = result.getData();
//                if (body != null) {
//                    BeanUtils.copyProperties(body, eventData);
//                }
//                log.debug("远程调用工作流启动接口成功：" + JSONObject.toJSONString(result));
//            }else{
//                String msg = String.format("远程调用工作流启动接口失败：【code=%s,message=%s】",
//                        result.getCode(), result.getMessage());
//                log.info(msg);
//            }
//        } catch (Exception e) {
//            log.error("远程调用工作流启动接口异常：", e);
//        }

        return EventResult.build(eventData);
    }


}
