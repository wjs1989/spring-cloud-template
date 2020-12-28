package com.wjs.xian.game;

import com.wjs.xian.model.Hero;
import lombok.Data;

public class HeroFactory {

    public static Builder newBuilder(){
        return new Builder();
    }

    public static class Builder{
        private String name;
        //攻击力
        private Integer atk;
        //防御力
        private Integer def;
        private Integer hp;
        private Integer mp;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAtk(Integer atk) {
            this.atk = atk;
            return this;
        }

        public Builder setDef(Integer def) {
            this.def = def;
            return this;
        }

        public Builder setHp(Integer hp) {
            this.hp = hp;
            return this;
        }

        public Builder setMp(Integer mp) {
            this.mp = mp;
            return this;
        }

        public Hero build(){
            Hero hero = new Hero();
            hero.setName(name);
            hero.setAtk(atk);
            hero.setDef(def);
            hero.setHp(hp);
            hero.setMp(mp);

            return hero;
        }

    }
}
