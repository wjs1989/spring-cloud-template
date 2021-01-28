package com.wjs.produce.proxy;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.target.EmptyTargetSource;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SpringProxyUtils {
    private SpringProxyUtils() {
    }

    public static Class<?> findTargetClass(Object proxy) throws Exception {
        if (AopUtils.isAopProxy(proxy)) {
            AdvisedSupport advised = getAdvisedSupport(proxy);
            if (AopUtils.isJdkDynamicProxy(proxy)) {
                TargetSource targetSource = advised.getTargetSource();
                return targetSource instanceof EmptyTargetSource ? getFirstInterfaceByAdvised(advised) : targetSource.getTargetClass();
            } else {
                Object target = advised.getTargetSource().getTarget();
                return findTargetClass(target);
            }
        } else {
            return proxy == null ? null : proxy.getClass();
        }
    }

    public static Class<?>[] findInterfaces(Object proxy) throws Exception {
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            AdvisedSupport advised = getAdvisedSupport(proxy);
            return getInterfacesByAdvised(advised);
        } else {
            return new Class[0];
        }
    }

    private static Class<?>[] getInterfacesByAdvised(AdvisedSupport advised) {
        Class<?>[] interfaces = advised.getProxiedInterfaces();
        if (interfaces.length > 0) {
            return interfaces;
        } else {
            throw new IllegalStateException("Find the jdk dynamic proxy class that does not implement the interface");
        }
    }

    private static Class<?> getFirstInterfaceByAdvised(AdvisedSupport advised) {
        Class<?>[] interfaces = advised.getProxiedInterfaces();
        if (interfaces.length > 0) {
            return interfaces[0];
        } else {
            throw new IllegalStateException("Find the jdk dynamic proxy class that does not implement the interface");
        }
    }

    public static AdvisedSupport getAdvisedSupport(Object proxy) throws Exception {
        Field h;
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            h = proxy.getClass().getSuperclass().getDeclaredField("h");
        } else {
            h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        }

        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        return (AdvisedSupport)advised.get(dynamicAdvisedInterceptor);
    }

    public static boolean isProxy(Object bean) {
        if (bean == null) {
            return false;
        } else {
            return DubboUtil.isDubboProxyName(bean.getClass().getName()) || Proxy.class.isAssignableFrom(bean.getClass()) || AopUtils.isAopProxy(bean);
        }
    }

    public static Class<?> getTargetInterface(Object proxy) throws Exception {
        if (proxy == null) {
            throw new IllegalArgumentException("proxy can not be null");
        } else if (Proxy.class.isAssignableFrom(proxy.getClass())) {
            Proxy p = (Proxy)proxy;
            return p.getClass().getInterfaces()[0];
        } else {
            return getTargetClass(proxy);
        }
    }

    protected static Class<?> getTargetClass(Object proxy) throws Exception {
        if (proxy == null) {
            throw new IllegalArgumentException("proxy can not be null");
        } else if (!AopUtils.isAopProxy(proxy)) {
            return proxy.getClass();
        } else {
            AdvisedSupport advisedSupport = getAdvisedSupport(proxy);
            Object target = advisedSupport.getTargetSource().getTarget();
            if (target == null) {
                return CollectionUtils.isNotEmpty(advisedSupport.getProxiedInterfaces()) ? advisedSupport.getProxiedInterfaces()[0] : proxy.getClass();
            } else {
                return getTargetClass(target);
            }
        }
    }

    public static Class<?>[] getAllInterfaces(Object bean) {
        Set<Class<?>> interfaces = new HashSet();
        if (bean != null) {
            for(Class clazz = bean.getClass(); !Object.class.getName().equalsIgnoreCase(clazz.getName()); clazz = clazz.getSuperclass()) {
                Class<?>[] clazzInterfaces = clazz.getInterfaces();
                interfaces.addAll(Arrays.asList(clazzInterfaces));
            }
        }

        return (Class[])interfaces.toArray(new Class[0]);
    }
}
