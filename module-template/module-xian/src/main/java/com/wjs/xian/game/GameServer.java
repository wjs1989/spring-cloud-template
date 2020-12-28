package com.wjs.xian.game;

import com.wjs.xian.model.Hero;

import javax.annotation.PostConstruct;
import java.util.Arrays;

public class GameServer {
    private GameEngine engine;

    public GameServer() {
        engine = new GameEngine();
    }

    @PostConstruct
    public void start(){

        Hero hero1 = HeroFactory.newBuilder()
                .setName("1号")
                .setAtk(10)
                .setDef(1)
                .setHp(100)
                .setMp(10)
                .build();

        Hero hero2 = HeroFactory.newBuilder()
                .setName("2号")
                .setAtk(9)
                .setDef(1)
                .setHp(100)
                .setMp(10)
                .build();

        engine.fight(Arrays.asList(hero1),Arrays.asList(hero2));
    }

}
