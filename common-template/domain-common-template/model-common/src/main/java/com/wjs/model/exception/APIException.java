package com.wjs.model.exception;

import com.wjs.model.constant.MessageEnum;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/24 11:44
 */
public class APIException extends RuntimeException {
    private Integer code;

    private MessageEnum messageEnum;

    public APIException(String message){
        super(message);
        this.code = MessageEnum.FAIL.getCode();
    }

    public APIException(String message,Throwable cause){
        super(message,cause);
        this.code = MessageEnum.FAIL.getCode();
    }

    public APIException(Integer code,String message,Throwable cause){
        super(message,cause);
        this.code = code;
    }

    public APIException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public APIException(MessageEnum messageEnum){
        super(messageEnum.getMsg());
        this.code = messageEnum.getCode();
    }

    public APIException(MessageEnum messageEnum,Throwable cause){
        super(messageEnum.getMsg(),cause);
        this.code = messageEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public MessageEnum getMessageEnum() {
        return messageEnum;
    }
}
