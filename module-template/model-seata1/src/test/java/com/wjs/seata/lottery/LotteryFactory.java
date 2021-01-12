package com.wjs.seata.lottery;

import java.util.*;

public class LotteryFactory {

    public static void main(String[] args) throws InterruptedException {

        Map<String, Integer> cache = new HashMap<>();
        for (int i = 0; i < 10000000; i++) {
            Lottery lottery = new Lottery();
            Thread.sleep(1);
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

        for(int i =0 ;i < 5;i++,it.hasNext()){
            Map.Entry<String, Integer> next = it.next();
            System.out.println(next.getKey() + "->" + next.getValue());
        }
    }
}
