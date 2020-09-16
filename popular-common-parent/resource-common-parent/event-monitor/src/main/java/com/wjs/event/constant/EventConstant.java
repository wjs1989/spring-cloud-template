package com.wjs.event.constant;

/**
 * @author wenjs
 * @Description:
 * @date 2020/9/1 9:40
 */
public class EventConstant {

    //-------------------------------------事件类型-------------------------
    // 前置
    public static String PRE_EVENT = "PREEVENT";

    // 后置
    public static String POST_EVENT = "POSTEVENT";


    //--------------------------------------事件字段--------------------------
    /**
     * 事件名称
     */
    public static String EVENT_NAME = "name";

    /**
     * 事件类型
     */
    public static String EVENT_TYPE = "type";

    /**
     * 参数列表
     */
    public final static String EVENT_PARAMS = "params";
    public final static String EVENT_EXTEND = "extend";

    public final static String EVENT_PARAMS_FIELD_KEY = "key";
    public final static String EVENT_PARAMS_FIELD_VALUE = "value";
    public final static String EVENT_PARAMS_FIELD_TYPE = "type";
    public final static String EVENT_PARAMS_FIELD_TYPE_SELECT= "select";
    public final static String EVENT_PARAMS_FIELD_TYPE_INPUT= "input";
    public final static String EVENT_PARAMS_FIELD_TYPE_EXTEND = "extend";
    public final static String EVENT_PARAMS_FIELD_TYPE_BANDING = "banding";
    public final static String EVENT_PARAMS_FIELD_TYPE_UNBANDING = "unbanding";
    public final static String EVENT_PARAMS_FIELD_TYPE_ACTUAL_VALUE = "actualvalue";

    //--------------------------------------事件名称--------------------------

    /**
     * 默认事件
     */
    public final static String DEFAULT = "DEFAULT";

    /**
     * 工作流启动事件
     */
    public final static String WORK_FLOW_START = "WORK_FLOW_START";
    public final static String WORK_FLOW_START_NAME = "工作流启动事件";

    public final static String COMMA = ",";
    public final static int ZERO = 0;
}