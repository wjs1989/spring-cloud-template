package com.wjs.produce.core.cglib;

import org.springframework.cglib.proxy.Enhancer;

public class CgLibProxy {

    public static void main(String[] args) {
        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(OperationService.class);
        //设置回调函数
        enhancer.setCallback(new DefaultMethodInterceptor());

        //这里的creat方法就是正式创建代理类
        OperationService proxyDog = (OperationService)enhancer.create();


        System.out.println(proxyDog.doBegin(1));


    }
}
