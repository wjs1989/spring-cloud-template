package com.wjs.produce.emum;

public enum MessageEnum implements BaseEnum {

    SYSTEM_FIAL("-1", "系统错误");

    MessageEnum(String key, String data) {
        this.key = key;
        this.data = data;
    }

    String key;
    String data;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getData() {
        return data;
    }
}
