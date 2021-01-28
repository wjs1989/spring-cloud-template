package com.wjs.produce.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class ReflectionUtil {
    public static final int MAX_NEST_DEPTH = 20;

    public ReflectionUtil() {
    }

    public static Class<?> getClassByName(String className) throws ClassNotFoundException {
        return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
    }

    public static Object getFieldValue(Object target, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Class<?> cl = target.getClass();
        int var3 = 0;

        while(var3++ < 20 && cl != null) {
            try {
                Field field = cl.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(target);
            } catch (Exception var5) {
                cl = cl.getSuperclass();
            }
        }

        throw new NoSuchFieldException("class:" + target.getClass() + ", field:" + fieldName);
    }

    public static Object invokeMethod(Object target, String methodName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> cl = target.getClass();
        int index = 0;

        while(index++ < MAX_NEST_DEPTH && cl != null) {
            try {
                Method m = cl.getDeclaredMethod(methodName);
                m.setAccessible(true);
                return m.invoke(target);
            } catch (Exception var5) {
                cl = cl.getSuperclass();
            }
        }

        throw new NoSuchMethodException("class:" + target.getClass() + ", methodName:" + methodName);
    }

    public static Object invokeMethod(Object target, String methodName, Class<?>[] parameterTypes, Object[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> cl = target.getClass();
        int index = 0;

        while(index++ < MAX_NEST_DEPTH && cl != null) {
            try {
                Method m = cl.getDeclaredMethod(methodName, parameterTypes);
                m.setAccessible(true);
                return m.invoke(target, args);
            } catch (Exception var7) {
                cl = cl.getSuperclass();
            }
        }

        throw new NoSuchMethodException("class:" + target.getClass() + ", methodName:" + methodName);
    }

    public static Object invokeStaticMethod(Class<?> targetClass, String methodName, Class<?>[] parameterTypes, Object[] parameterValues) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int index = 0;

        while(index++ < 20 && targetClass != null) {
            try {
                Method m = targetClass.getMethod(methodName, parameterTypes);
                return m.invoke((Object)null, parameterValues);
            } catch (Exception var6) {
                targetClass = targetClass.getSuperclass();
            }
        }

        throw new NoSuchMethodException("class:" + targetClass + ", methodName:" + methodName);
    }

    public static Method getMethod(Class<?> classType, String methodName, Class<?>[] parameterTypes) throws NoSuchMethodException, SecurityException {
        return classType.getMethod(methodName, parameterTypes);
    }

    public static Set<Class<?>> getInterfaces(Class<?> clazz) {
        if (clazz.isInterface()) {
            return Collections.singleton(clazz);
        } else {
            LinkedHashSet interfaces;
            for(interfaces = new LinkedHashSet(); clazz != null; clazz = clazz.getSuperclass()) {
                Class<?>[] ifcs = clazz.getInterfaces();
                for(int index = 0; index < ifcs.length; ++index) {
                    Class<?> ifc = ifcs[index];
                    interfaces.addAll(getInterfaces(ifc));
                }
            }

            return interfaces;
        }
    }

    public static void modifyStaticFinalField(Class cla, String modifyFieldName, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = cla.getDeclaredField(modifyFieldName);
        field.setAccessible(true);
        Field modifiers = field.getClass().getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & -17);
        field.set(cla, newValue);
    }
}
