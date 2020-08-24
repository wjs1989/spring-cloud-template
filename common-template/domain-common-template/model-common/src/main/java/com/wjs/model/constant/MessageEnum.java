package com.wjs.model.constant;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/24 11:35
 */
public enum MessageEnum {

    FAIL(-1,"失败"),
    SUCCESS(0, "成功");


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
