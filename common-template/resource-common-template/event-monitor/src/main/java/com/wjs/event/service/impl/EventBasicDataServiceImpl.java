package com.wjs.event.service.impl;

import com.wjs.event.BaseEventHandler;
import com.wjs.event.EventHandlerFactory;
import com.wjs.event.model.EventBasicDataModel;
import com.wjs.event.service.EventBasicDataService;
import com.wjs.event.vo.EventBasicDataResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EventBasicDataServiceImpl implements EventBasicDataService {

    @Autowired
    private EventHandlerFactory eventHandlerFactory;

    @Override
    public List<EventBasicDataResp> getEventBasicData() {
        List<EventBasicDataResp> respList = new ArrayList<>();
        List<BaseEventHandler> allBaseEventHandler = eventHandlerFactory.getAllBaseEventHandler();
        if(allBaseEventHandler != null && !allBaseEventHandler.isEmpty()){
            allBaseEventHandler.forEach((event)->{
                try{
                    EventBasicDataModel params = event.getParams();
                    EventBasicDataResp resp = new EventBasicDataResp();
                    BeanUtils.copyProperties(params,resp);
                    respList.add(resp);
                }catch (Exception e){
                    log.error("组装事件参数异常",e);
                }
            });
        }

        return respList;
    }
}
