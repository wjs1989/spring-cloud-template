package com.wjs.seckill.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class SeckillTemplate implements Connection {

    Connection proxyInstall;

    public SeckillTemplate() {
        proxyInstall = (Connection) Proxy.newProxyInstance(SeckillTemplate.class.getClassLoader(), new Class[]{Connection.class}, new SeckillInterceptor());
    }

    @Override
    public void doStart(String name) {
        this.proxyInstall.doStart(name);
    }

    private class SeckillInterceptor implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //这里可以生成不同的执行对象

            DefaultSeckill defaultSeckill = new DefaultSeckill();
            Object result = new Object();
            Advisor[] advisors = new Advisor[10];
            for (Advisor advisor : advisors) {
                result = advisor.invoke(null);
            }

            return result;
        }
    }

}
