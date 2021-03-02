package com.wjs.produce;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

/**
 * @author wenjs
 */
public class ApplicationContextTest {

    @Test
    public void text(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationContextTest.class);

    }
}
