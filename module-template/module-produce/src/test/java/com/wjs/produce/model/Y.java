package com.wjs.produce.model;

import javax.annotation.PostConstruct;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 10:27
 */
public class Y {
    public Y(){
        System.out.println("y -------> instance");
    }

    @PostConstruct
    public void init(){
        System.out.println("y -------> init");

    }
}
