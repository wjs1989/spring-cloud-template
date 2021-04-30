package com.wjs.mqtt.process;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjs
 */
public class ProcessManage {

    public static Map<String,IProcess> processMap= new HashMap<>();

    public static void  registerProcess(){
        if (ProcessManage.processMap.isEmpty()) {
            ProcessManage.processMap.put("/read-property", new ReadPropertyProcess());
            ProcessManage.processMap.put("/write-property", new WritePropertyProcess());
            ProcessManage.processMap.put("/invoke-function", new InvokeFunctionProcess());
        }
    }
}
