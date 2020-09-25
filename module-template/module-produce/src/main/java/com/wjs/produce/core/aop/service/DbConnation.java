package com.wjs.produce.core.aop.service;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
//@Component
public class DbConnation {

    public int exec(String sql){
        System.out.println("执行sql："+sql);

        return 1;
    }
}
