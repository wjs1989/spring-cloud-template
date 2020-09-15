package com.wjs.model.constant;

import com.sun.org.apache.bcel.internal.classfile.Unknown;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/24 11:35
 */
public enum MessageEnum {

    FAIL(-1,"失败"),
    SUCCESS(0, "成功"),

    //100 001 001
    EVENT_PARAMS_CONVERT_ERROR(100001001,"事件参数解析异常"),
    EVENT_TYPE_CONVERT_ERROR(100001002,"事件类型解析异常"),







    //未知异常
    UNKNOWN_ERROR(-2,"未知异常");

    private Integer code;
    private String msg;

    private MessageEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

}
