package com.wjs.produce.core.proxy.impl;

import com.wjs.produce.core.proxy.IConnection;

/**
 * @ClassName DefaultConnection
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/23
 * @Version V1.0
 **/
public class DefaultConnection implements IConnection {
    @Override
    public void doConnection(String url) {
        System.out.println(url);
    }
}
