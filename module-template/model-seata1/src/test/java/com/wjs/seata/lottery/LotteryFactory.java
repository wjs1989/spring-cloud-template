package com.wjs.seata.lottery;

import java.util.*;

public class LotteryFactory {

    public static void main(String[] args) {

        Map<String, Integer> cache = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            Lottery lottery = new Lottery();
            lottery.init();
            String key = lottery.toString();
            if (cache.containsKey(key)) {
                cache.put(key, cache.get(key) + 1);
            } else {
                cache.put(key, 1);
            }
        }

        List<Map.Entry<String,Integer>> list=new ArrayList<>();
        list.addAll(cache.entrySet());

        Collections.sort(list,(a,b)->a.getValue()-b.getValue());

         Iterator<Map.Entry<String,Integer>> it=list.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next().getKey());
//        }

        for(int i =0 ;i < 4;i++,it.hasNext()){
            System.out.println(it.next().getKey());
        }
    }
}
