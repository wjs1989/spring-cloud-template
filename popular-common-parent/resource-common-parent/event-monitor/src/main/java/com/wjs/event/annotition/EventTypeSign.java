package com.wjs.event.annotition;

import java.lang.annotation.*;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:06
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventTypeSign {

    //中文名称
    String name() default "";

    //标签
    String value() default "";

    //PREEVENT  POSTEVENT
    String type() default "POSTEVENT";
}

