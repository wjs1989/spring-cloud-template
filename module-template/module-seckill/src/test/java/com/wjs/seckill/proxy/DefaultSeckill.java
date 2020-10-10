package com.wjs.seckill.proxy;

public class DefaultSeckill implements Connection {
    @Override
    public void doStart(String name) {
        System.out.println(name + "---------------------------");
    }
}
