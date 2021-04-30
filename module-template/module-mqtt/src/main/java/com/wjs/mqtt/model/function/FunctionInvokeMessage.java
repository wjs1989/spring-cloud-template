package com.wjs.mqtt.model.function;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author wenjs
 */
@Getter
@Setter
public class FunctionInvokeMessage {
    Map<String,Object> headers;
    String function;//功能标识,在元数据中定义.
    String deviceId;
    String messageId;
    long timestamp; //时间戳(毫秒)
    List<FunctionParameter> args;//输入参数
}
