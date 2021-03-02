package com.wjs.seata.lottery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class LotteryFactory {

    public static void main(String[] args) throws Exception {

        Set<String> historyData = createHistoryData();

        Map<String, Integer> cache = new HashMap<>(10000000);
        for (long i = 0; i < 1000000000L; i++) {
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

        Map<String, Integer> cacheNew = cache.entrySet().stream().filter(c -> c.getValue().equals(1)).collect(Collectors.toMap(c -> c.getKey(), c -> c.getValue()));

        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(cacheNew.entrySet());

//         Collections.sort(list, (a, b) -> a.getValue() - b.getValue());
//
//         Iterator<Map.Entry<String, Integer>> it = list.iterator();
// //        while (it.hasNext()){
// //            System.out.println(it.next().getKey());
// //        }
//
//         for (int i = 0; ; i++, it.hasNext()) {
//             Map.Entry<String, Integer> next = it.next();
//             if(next.getValue().equals(1)){
//                 System.out.println(next.getKey() + "->" + next.getValue());
//             }else{
//                 return;
//             }
//         }

        int size = list.size();
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int index = random.nextInt(size * 37) * 37 % size;
            Map.Entry<String, Integer> next = list.get(index);
            System.out.println(next.getKey() + "->" + next.getValue() + "->" + index);
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
