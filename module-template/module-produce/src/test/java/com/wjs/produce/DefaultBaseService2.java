package com.wjs.produce;

public class DefaultBaseService2 implements BaseService1<Integer>{
    @Override
    public void doService(Integer a) {

        System.out.println("DefaultBaseService2"+a);
    }
}
