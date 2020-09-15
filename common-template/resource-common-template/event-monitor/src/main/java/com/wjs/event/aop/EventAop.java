package com.wjs.event.aop;

import com.wjs.event.EventHandlerFactory;
import com.wjs.event.constant.EventConstant;
import com.wjs.event.model.EventBaseParams;
import com.wjs.event.model.EventData;
import com.wjs.event.model.EventDataManager;
import com.wjs.event.model.EventResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:15
 */
@Component
@Aspect
public class EventAop {

    @Autowired
    private EventHandlerFactory eventHandlerFactory;

    @Pointcut("@annotation(com.wjs.event.annotition.EventHandler)")
    private void point() {
    }


    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        eventHandler(args, EventConstant.PRE_EVENT);
        Object result = joinPoint.proceed(args);
        eventHandler(args,EventConstant.POST_EVENT);
        return result;
    }

    /**
     * 事件分发处理
     * @param args
     * @param type
     */
    private void eventHandler(Object[] args,String type){

        if (args == null || args.length == EventConstant.ZERO) {
            return;
        }

        for (Object arg : args) {
            if (EventBaseParams.class.isAssignableFrom(arg.getClass())) {
                EventBaseParams eventBaseParams = (EventBaseParams) arg;
                List<HashMap<String, Object>> eventMaps = eventBaseParams.getEvents();
                if (eventMaps == null || eventMaps.isEmpty()) {
                    continue;
                }

                for (HashMap map : eventMaps) {
                    String name = String.valueOf(map.get(EventConstant.EVENT_NAME));
                    String eventType = String.valueOf(map.get(EventConstant.EVENT_TYPE));
                    if(type.equals(eventType)){
                        EventResult result = eventHandlerFactory.getEventHandler(name).exec(map);
                        if(result != null){
                            EventData eventData = result.getResult();
                            eventData.setName(name);
                            eventData.setType(eventType);
                            EventDataManager.putEventData(name,eventData);
                        }
                    }
                }
            }
        }
    }

}
