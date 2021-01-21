package com.wjs.produce;

public class Generic<T> {
    T data;

    public Generic(T data) {
        setData(data);
    }

    public Generic() {

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
