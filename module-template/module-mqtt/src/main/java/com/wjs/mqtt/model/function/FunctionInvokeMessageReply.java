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
public class FunctionInvokeMessageReply {
    Map<String,Object> headers;
    String deviceId;
    String messageId;
    long timestamp;
    boolean success;
    String functionId;
    Object output; //输出值,需要与元数据定义中的类型一致

}
