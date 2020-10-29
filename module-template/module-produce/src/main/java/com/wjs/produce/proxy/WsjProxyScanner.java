package com.wjs.produce.proxy;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Component
public class WsjProxyScanner extends AbstractAutoProxyCreator {
    private static final Set<String> PROXYED_SET = new HashSet();

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> aClass, String s, TargetSource targetSource) throws BeansException {
        return new Object[]{new WjsProxyInterceptor()};
    }

    @Override
    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        try {
            synchronized (PROXYED_SET) {
                if (PROXYED_SET.contains(beanName)) {
                    return bean;
                } else {
                    if (!this.existsAnnotation(bean.getClass())) {
                        return bean;
                    }
                    bean = super.wrapIfNecessary(bean, beanName, cacheKey);
                    PROXYED_SET.add(beanName);
                    return bean;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean existsAnnotation(Class clazz) {
//        ReflectionUtils.doWithMethods(classes,(method)->{
//            DefProxy defProxy = method.getAnnotation(DefProxy.class);
//            if(defProxy != null){
//                return true;
//            }
//        });
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            DefProxy defProxy = method.getAnnotation(DefProxy.class);
            if (defProxy != null) {
                return true;
            }
        }
        return false;
    }
}
