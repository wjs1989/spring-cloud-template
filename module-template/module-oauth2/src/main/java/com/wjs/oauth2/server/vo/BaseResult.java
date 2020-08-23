package com.wjs.oauth2.server.vo;

import lombok.Data;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/20 10:44
 */

@Data
public class BaseResult<T> {

    private Integer code;

    private String message;

    private T data;

    public BaseResult() {
        // TODO Auto-generated constructor stub
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
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
}
