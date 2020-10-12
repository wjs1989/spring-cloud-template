package com.wjs.produce.config;

import com.wjs.produce.model.X;
import com.wjs.produce.model.Y;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 10:08
 */
@Configuration
public class AppConfig {

//    @Bean
//    public X getx(){
//        return new X();
//    }

    @Bean
    public Y gety(){
        return new Y();
    }
}
