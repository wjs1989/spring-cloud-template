package com.wjs.produce;

import com.wjs.produce.model.X;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class VolatileTest {

    @Autowired
    public X x1;

    @Autowired
    public X x2;

    @Test
    public void text(){
        // System.out.println(x1.getName());
        // System.out.println(x2.getName());
    }

}
