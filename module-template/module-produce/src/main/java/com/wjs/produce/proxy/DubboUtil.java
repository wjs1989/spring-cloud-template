package com.wjs.produce.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class DubboUtil {
    private static final String ALIBABA_DUBBO_PROXY_NAME_PREFIX = "com.alibaba.dubbo.common.bytecode.proxy";
    private static final String APACHE_DUBBO_PROXY_NAME_PREFIX = "org.apache.dubbo.common.bytecode.proxy";

    private DubboUtil() {
    }

    public static Class<?> getAssistInterface(Object proxyBean) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (proxyBean == null) {
            return null;
        } else if (!isDubboProxyName(proxyBean.getClass().getName())) {
            return null;
        } else {
            Field handlerField = proxyBean.getClass().getDeclaredField("handler");
            handlerField.setAccessible(true);
            Object invokerInvocationHandler = handlerField.get(proxyBean);
            Field invokerField = invokerInvocationHandler.getClass().getDeclaredField("invoker");
            invokerField.setAccessible(true);
            Object invoker = invokerField.get(invokerInvocationHandler);
            Field failoverClusterInvokerField = invoker.getClass().getDeclaredField("invoker");
            failoverClusterInvokerField.setAccessible(true);
            Object failoverClusterInvoker = failoverClusterInvokerField.get(invoker);
            return (Class)ReflectionUtil.invokeMethod(failoverClusterInvoker, "getInterface");
        }
    }

    public static boolean isDubboProxyName(String name) {
        return name.startsWith("com.alibaba.dubbo.common.bytecode.proxy") || name.startsWith("org.apache.dubbo.common.bytecode.proxy");
    }
}
