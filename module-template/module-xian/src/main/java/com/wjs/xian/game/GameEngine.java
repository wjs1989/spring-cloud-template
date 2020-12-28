package com.wjs.xian.game;

import com.wjs.xian.model.Hero;

import java.util.Iterator;
import java.util.List;

/**
 * 引擎
 */
public class GameEngine {
    private int EVEN = -1;
    private int ZEROWIN = 0;
    private int ONEWIN = 1;


    /**
     * @param heroa
     * @param herob
     * @return -1 中立 0->0胜 1->1胜
     */
    public int fight(List<Hero> heroa, List<Hero> herob) {
        if (heroa == null && herob == null && heroa.isEmpty() && herob.isEmpty()) {
            return EVEN;
        }
        if (heroa != null && !heroa.isEmpty() && (herob == null || herob.isEmpty())) {
            return ZEROWIN;
        }
        if (herob != null && !herob.isEmpty() && (heroa == null || heroa.isEmpty())) {
            return ONEWIN;
        }

        Iterator<Hero> iteratora = heroa.iterator();
        Iterator<Hero> iteratorb = herob.iterator();
        Hero ha = iteratora.next();
        Hero hb = iteratorb.next();
        for (; ; ) {
            int fight = fight(ha, hb);
            if (fight == ONEWIN) {
                if (!iteratora.hasNext()) {
                    return ONEWIN;
                }
                ha = iteratora.next();
            } else if (fight == ZEROWIN) {
                if (!iteratorb.hasNext()) {
                    return ZEROWIN;
                }
                hb = iteratorb.next();
            } else {
                if (!iteratora.hasNext() && !iteratorb.hasNext()) {
                    return EVEN;
                } else if (!iteratora.hasNext()) {
                    return ONEWIN;
                } else if (!iteratorb.hasNext()) {
                    return ZEROWIN;
                }
                ha = iteratora.next();
                hb = iteratorb.next();
            }
        }
    }

    /**
     * @param heroa
     * @param herob
     * @return 0->0胜 1->1胜
     */
    public int fight(Hero heroa, Hero herob) {
        int rounds = 0;
        for(;;){
            if(rounds % 2 == 0){
                realFight(heroa,herob);
                print(herob);
                if(herob.getHp() <= 0){
                    return ONEWIN;
                }
            }else{
                realFight(herob,heroa);
                print(heroa);
                if(heroa.getHp() <= 0){
                    return ZEROWIN;
                }
            }
            rounds++;
        }
    }

    private void realFight(Hero heroa, Hero herob) {
        Integer atk = heroa.getAtk();
        Integer def = herob.getDef();
        Integer hp = atk - def;
        hp = hp < 0 ? 1 : hp;
        herob.setHp(herob.getHp()- hp);
    }

    private void print(Hero hero) {
        System.out.println(hero.print().toString());
    }
}
