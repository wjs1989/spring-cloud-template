package com.wjs.event.impl;

import com.wjs.event.AbstractEventHandler;
import com.wjs.event.model.Event;
import com.wjs.event.model.EventResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 17:11
 */
//@EventTypeSign(name = "默认事件", value = "DEFAULT")
//@Component
public class DefaultEventHandler extends AbstractEventHandler<Event> {
    private Logger logger =  LoggerFactory.getLogger(DefaultEventHandler.class);

    @Override
    protected EventResult preProcess(Event event) {
        logger.debug("DefaultEventHandler -----------------------> preProcess");

        return null;
    }

    @Override
    protected EventResult postProcess(Event event) {
        logger.debug("DefaultEventHandler -----------------------> postProcess");

        return null;
    }
}
