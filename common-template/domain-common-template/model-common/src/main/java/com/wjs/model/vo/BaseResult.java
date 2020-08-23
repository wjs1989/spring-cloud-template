package com.wjs.model.vo;

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
        return new BaseResult(0,"请求成功",data);
    }

    public static BaseResult success(){
        return new BaseResult(0,"请求成功");
    }

    public static BaseResult error(){
        return new BaseResult(-1,"请求失败");
    }
    public static BaseResult error(String message){
        return new BaseResult(-1,message);
    }
    public static BaseResult error(Integer code,String message){
        return new BaseResult(code,message);
    }
}
