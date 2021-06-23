package com.wjs.seata.cla;
import  java.lang.String;
/**
 * @author wenjs
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader load = new MyClassLoader();
        Class<?> aClass = load.loadClass("java.lang.String");
        Object o = aClass.newInstance();


        System.out.println(o);
    }

}
