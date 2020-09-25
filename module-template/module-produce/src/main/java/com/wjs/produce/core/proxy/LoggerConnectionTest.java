package com.wjs.produce.core.proxy;

import com.wjs.produce.core.proxy.impl.DefaultConnection;

/**
 * @ClassName LoggerConnectionTest
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/23
 * @Version V1.0
 **/
public class LoggerConnectionTest {

    public static void main(String[] args) {
        IConnection connection = LoggerConnection.newInstance(new DefaultConnection());

        connection.doConnection("www.wjs.com");
    }
}
