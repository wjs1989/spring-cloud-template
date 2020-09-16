package com.wjs.model.vo;

import com.wjs.model.constant.MessageEnum;
import lombok.Data;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/20 10:44
 */

@Data
public class BaseResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public BaseResult() {
    }

    public BaseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> BaseResult success(T data){
        return buildCode(MessageEnum.SUCCESS,data);
    }
    public static BaseResult success(){
        return buildCode(MessageEnum.SUCCESS);
    }

    public static BaseResult error(){
        return  buildCode(MessageEnum.FAIL);
    }
    public static BaseResult error(MessageEnum messageEnum){
        return buildCode(messageEnum);
    }
    public static BaseResult error(String message){
        return new BaseResult(MessageEnum.FAIL.getCode(),message);
    }
    public static BaseResult error(Integer code,String message){
        return new BaseResult(code,message);
    }

    private static BaseResult buildCode(MessageEnum messageEnum){
        return new BaseResult(messageEnum.getCode(),messageEnum.getMsg());
    }

    private static <T> BaseResult buildCode(MessageEnum messageEnum,T data){
        return new BaseResult(messageEnum.getCode(),messageEnum.getMsg(),data);
    }
}
