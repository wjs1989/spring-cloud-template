package com.wjs.netty.netty3;

/**
 * @author wenjs
 */
public class RPCMessage {
    
    byte code;

    byte type;
    
    String message;


    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
