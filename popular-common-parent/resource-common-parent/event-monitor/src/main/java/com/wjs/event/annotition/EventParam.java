package com.wjs.event.annotition;


import com.wjs.event.constant.EventConstant;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventParam {
    /**
     * 字段中文说明
     * @return
     */
    String value() default "";

    /**
     * 字段名称
     * @return
     */
    String key() default "";

    /**
     * input select banding
     * @return
     */
    String type() default EventConstant.EVENT_PARAMS_FIELD_TYPE_INPUT;

    /**
     *
     * @return
     */
    boolean request() default true;
}
