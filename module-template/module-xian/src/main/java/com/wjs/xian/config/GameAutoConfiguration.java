package com.wjs.xian.config;

import com.wjs.xian.game.GameServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameAutoConfiguration {

    @Bean
    public GameServer gameServer(){
        return new GameServer();
    }

}
