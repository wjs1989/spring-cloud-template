package com.wjs.event.annotition;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventParams {
    EventParam[] value();
}
