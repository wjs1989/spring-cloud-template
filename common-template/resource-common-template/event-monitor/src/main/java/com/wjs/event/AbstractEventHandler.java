package com.wjs.event;

import com.wjs.event.annotition.EventParam;
import com.wjs.event.annotition.EventParams;
import com.wjs.event.annotition.EventTypeSign;
import com.wjs.event.constant.EventConstant;
import com.wjs.event.model.*;
import com.wjs.model.constant.MessageEnum;
import com.wjs.model.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author wenjs
 * @Description:
 * @date 2020/9/1 9:35
 */
public abstract class AbstractEventHandler<E extends Event> implements com.wjs.event.BaseEventHandler {
    private Logger logger = LoggerFactory.getLogger(com.wjs.event.AbstractEventHandler.class);

    /**
     * 泛型的 class，方便后续执行事件时，实例化事件参数
     */
    private Class<E> clazz;

    public AbstractEventHandler() {

        // generic 泛型
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType) type;
            clazz = (Class<E>) ptype.getActualTypeArguments()[0];
        }
    }

    @Override
    public EventResult exec(Map eventMap) {
        E event = null;
        try {
            Map<String, Object> param = parseParams(eventMap);
            event = clazz.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(event, param);
        } catch (InstantiationException e) {
            //执行全局异常
            throw new GlobalException(MessageEnum.EVENT_PARAMS_CONVERT_ERROR);
        } catch (IllegalAccessException e) {
            //执行全局异常
            throw new GlobalException(MessageEnum.EVENT_PARAMS_CONVERT_ERROR);
        } catch (InvocationTargetException e) {
            //执行全局异常
            throw new GlobalException(MessageEnum.EVENT_PARAMS_CONVERT_ERROR);
        }

        if (EventConstant.PRE_EVENT.equals(event.getType())) {
            return preProcess(event);
        } else if (EventConstant.POST_EVENT.equals(event.getType())) {
            return postProcess(event);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("AbstractEventHandler ---> 未知的事件类型");
            }
            //执行全局异常
            throw new GlobalException(MessageEnum.EVENT_TYPE_CONVERT_ERROR);
        }
    }

    @Override
    public EventBasicDataModel getParams() {
        EventBasicDataModel model = new EventBasicDataModel();
        if (clazz == null) {
            return model;
        }

        EventTypeSign eventTypeSign = this.getClass().getAnnotation(EventTypeSign.class);
        if (eventTypeSign == null) {
            return model;
        }
        model.setName(eventTypeSign.name());
        model.setValue(eventTypeSign.value());
        model.setType(eventTypeSign.type());
        //这里需要校验参数配置是否重复，以第一个为准
        //先查看类头是否有EventParams注解，有的话先解析
        //在遍历字段，有注解EventParam的解析
        //如果是存在select字段的，调用方法getSelect解析
        Set<String> paramSet = new HashSet<>();
        EventParams eventParams = clazz.getAnnotation(EventParams.class);
        if (eventParams != null) {
            EventParam[] paramList = eventParams.value();
            if (paramList != null && paramList.length > EventConstant.ZERO) {
                for (EventParam ep : paramList) {
                    String key =ep.key();
                    if (StringUtils.isNotBlank(key) && !paramSet.contains(key) && paramSet.add(key)) {
                        model.addParam(createBasic(key, ep));
                    }
                }
            }
        }

        ReflectionUtils.doWithLocalFields(clazz, field -> {
            EventParam ep = field.getAnnotation(EventParam.class);
            if (ep != null) {
                String key = StringUtils.isBlank(ep.key()) ? field.getName() : ep.key();
                if (!paramSet.contains(key) && paramSet.add(key)) {
                    model.addParam(createBasic(key, ep));
                }
            }
        });

        return model;
    }

    private EventBasicParam createBasic(String key, EventParam ep) {
        EventBasicParam ebp = new EventBasicParam();
        ebp.setName(ep.value());
        ebp.setValue(key);
        ebp.setRequest(ep.request());
        ebp.setType(ep.type());
        if (EventConstant.EVENT_PARAMS_FIELD_TYPE_SELECT.equals(ep.type())) {
            List<EventBasicSelect> select = getSelect(key);
            ebp.setList(select);
        }
        return ebp;
    }

    private Map parseParams(Map source) {
        Map<String, Object> candidateParam = new HashMap<>();

        candidateParam.put(EventConstant.EVENT_NAME, source.get(EventConstant.EVENT_NAME));
        candidateParam.put(EventConstant.EVENT_TYPE, source.get(EventConstant.EVENT_TYPE));

        //解析参数列表
        List<Map> params = (List<Map>) source.get(EventConstant.EVENT_PARAMS);

        if (params != null && !params.isEmpty()) {
            for (Map map : params) {
                if(map == null){
                    continue;
                }
                String type = String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_TYPE));
                if (EventConstant.EVENT_PARAMS_FIELD_TYPE_UNBANDING.equals(type)||
                        EventConstant.EVENT_PARAMS_FIELD_TYPE_SELECT.equals(type)||
                        EventConstant.EVENT_PARAMS_FIELD_TYPE_INPUT.equals(type)) {
                    //非绑定字段处理
                    candidateParam.put(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_KEY)), map.get(EventConstant.EVENT_PARAMS_FIELD_VALUE));
                } else if (EventConstant.EVENT_PARAMS_FIELD_TYPE_BANDING.equals(type)) {
                    //绑定字段处理
                    Object actualvalue = map.get(EventConstant.EVENT_PARAMS_FIELD_TYPE_ACTUAL_VALUE);
                    if (actualvalue != null) {
                        //存在实际值，则直接填写
                        candidateParam.put(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_KEY)), actualvalue);
                    } else {
                        //在业务数据缓存中查找，这部分数据需要我们手动填写进去
                        String cloumn = String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_VALUE));
                        Object businessData = EventDataManager.getBusinessData(cloumn);
                        if (businessData == null) {
                            int idx = cloumn.lastIndexOf(EventConstant.COMMA);
                            if (idx > EventConstant.ZERO) {
                                cloumn = cloumn.substring(EventConstant.ZERO, idx);
                                businessData = EventDataManager.getBusinessData(cloumn);
                            }
                        }
                        candidateParam.put(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_KEY)), businessData);
                    }
                } else if (EventConstant.EVENT_PARAMS_FIELD_TYPE_EXTEND.equals(type)) {

                    //扩展字段，是写在Event的extend字段的，字段名与数量都是非固定的
                    Map extendMap = (Map) candidateParam.get(EventConstant.EVENT_EXTEND);
                    if (extendMap == null) {
                        extendMap = new HashMap<String, Object>();
                        candidateParam.put(EventConstant.EVENT_EXTEND, extendMap);
                    }
                    extendMap.put(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_KEY)), map.get(EventConstant.EVENT_PARAMS_FIELD_VALUE));

                } else {

                    //其他类型字段处理
                }
            }
        }

        return candidateParam;
    }

    /**
     * 前置事件，由子类实现
     *
     * @param event
     */
    protected abstract EventResult preProcess(E event);


    /**
     * 后置事件，由子类实现
     *
     * @param event
     */
    protected abstract EventResult postProcess(E event);


    protected List<EventBasicSelect> getSelect(String filed) {
        return null;
    }
}
