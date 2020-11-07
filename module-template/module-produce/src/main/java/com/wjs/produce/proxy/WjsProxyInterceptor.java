package com.wjs.produce.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author rtck
 */
public class WjsProxyInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("执行到:"+methodInvocation.getMethod().getName());
        return methodInvocation.proceed();
    }
}
