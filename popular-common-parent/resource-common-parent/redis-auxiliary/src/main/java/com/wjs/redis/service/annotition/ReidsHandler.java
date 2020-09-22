package com.wjs.redis.service.annotition;

import java.lang.annotation.*;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 17:16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReidsHandler {

    String value() default "";
}
