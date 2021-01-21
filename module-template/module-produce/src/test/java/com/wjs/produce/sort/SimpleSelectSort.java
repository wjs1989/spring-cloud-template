package com.wjs.produce.sort;

import java.util.List;

/**
 * 简单选择排序
 * 时间复杂度 O(n²)
 */
public class SimpleSelectSort extends BaseSort {
    static SimpleSelectSort INSTANCE = new SimpleSelectSort();


    public static void main(String[] args) {
        List<Integer> originalList = ArrayListData.originalList;
        INSTANCE.sort(originalList);
        INSTANCE.print(originalList);
    }

    @Override
    public void sort(List<Integer> list) {
        int size = list.size();
        int count =0;
        boolean swap = true;

        for (int i = 0; i < size; i++) {
            int minIndex = i;
            swap = false;
            for (int j = i; j < size; j++) {
                Integer a = list.get(minIndex);
                Integer b = list.get(j);
                if (a > b) {
                    minIndex = j;
                    swap = true;
                }
                count++;
            }
            Integer min = list.get(minIndex);
            list.set(minIndex, list.get(i));
            list.set(i,min);

            if(!swap){
                break;
            }
        }

        System.out.println(count);
    }

}
