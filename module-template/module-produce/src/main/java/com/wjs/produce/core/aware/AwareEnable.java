package com.wjs.produce.core.aware;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AwareBean.class)
public @interface AwareEnable {
    String value();
}
