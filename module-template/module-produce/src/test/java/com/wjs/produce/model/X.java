package com.wjs.produce.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 10:08
 */

public class X {

    public X(  ){
        System.out.println("x -------> instance");
    }

    @PostConstruct
    public void init(){
        System.out.println("x -------> init");
    }
}
