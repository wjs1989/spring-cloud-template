package com.wjs.seata.lottery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class LotteryFactory {

    public static void main(String[] args) throws Exception {

        Set<String> historyData = createHistoryData();

        Map<String, Integer> cache = new HashMap<>();
        for (int i = 0; i < 100000000; i++) {
            Lottery lottery = new Lottery();
            lottery.init();
            String key = lottery.toString();
            if (historyData.contains(key)) {
                continue;
            }

            if (cache.containsKey(key)) {
                cache.put(key, cache.get(key) + 1);
            } else {
                cache.put(key, 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(cache.entrySet());

        Collections.sort(list, (a, b) -> a.getValue() - b.getValue());

        Iterator<Map.Entry<String, Integer>> it = list.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next().getKey());
//        }

        for (int i = 0; i < 5; i++, it.hasNext()) {
            Map.Entry<String, Integer> next = it.next();
            System.out.println(next.getKey() + "->" + next.getValue());
        }
    }

    private static Set<String> createHistoryData() throws Exception {
        Set<String> sb = new HashSet<>();
        FileReader fr = new FileReader("D:\\aa.txt");
        BufferedReader bf = new BufferedReader(fr);
        String str;
        // 按行读取字符串
        while ((str = bf.readLine()) != null) {
            sb.add(str.split(",")[0]);
        }
        bf.close();
        fr.close();
        return sb;
    }
}
