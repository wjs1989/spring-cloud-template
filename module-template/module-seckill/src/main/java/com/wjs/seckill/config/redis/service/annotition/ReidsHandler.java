package com.wjs.seckill.config.redis.service.annotition;

import java.lang.annotation.*;

/**
 * @author wenjs
 * @Description: 使用当前注解修饰，表示需要支持自定义事件
 * @date 2020/8/31 17:16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReidsHandler {

    String value() default "";
}
