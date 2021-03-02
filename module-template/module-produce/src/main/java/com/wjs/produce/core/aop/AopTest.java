package com.wjs.produce.core.aop;

import com.wjs.produce.core.aop.service.DbConnation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
//@ComponentScan("com.wjs.produce")
public class AopTest {

    @Autowired
    private DbConnation dbConnation;

    @Autowired
    ApplicationContext applicationContext;

    public void contextLoads(){

       // ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.wjs.produce.core.aop");
       // DbConnation dbConnation = (DbConnation) applicationContext.getBean("dbConnation");
        dbConnation.exec("select 1 from wjs_t");
    }
}
