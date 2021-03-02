package com.wjs.produce;

public class DefaultBaseService1 implements BaseService1<Integer>{
    @Override
    public void doService(Integer a) {
        System.out.println("DefaultBaseService1"+a);
    }
}
