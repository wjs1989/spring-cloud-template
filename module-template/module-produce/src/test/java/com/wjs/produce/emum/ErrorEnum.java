package com.wjs.produce.emum;

public enum ErrorEnum implements BaseEnum {

    SUCCESS("0", "成功");

    ErrorEnum(String key, String data) {
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
