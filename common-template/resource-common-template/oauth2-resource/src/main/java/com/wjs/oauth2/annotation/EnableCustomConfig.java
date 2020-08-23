package com.wjs.oauth2.annotation;

import com.wjs.oauth2.config.ApplicationConfig;
import com.wjs.oauth2.config.SecurityImportBeanDefinitionRegistrar;
import com.wjs.oauth2.feign.OAuth2FeignConfig;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
//import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 开启线程异步执行
//@EnableAsync
// 自动加载类
@Import({ SecurityImportBeanDefinitionRegistrar.class, OAuth2FeignConfig.class, ApplicationConfig.class })
public @interface EnableCustomConfig
{

}
