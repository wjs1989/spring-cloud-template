package com.wjs.produce.sort;

import java.util.List;

/**
 * 冒泡排序
 * 时间复杂度 O(n²)
 */
public class BubbleSort extends BaseSort {
    static BubbleSort INSTANCE = new BubbleSort();


    public static void main(String[] args) {
        List<Integer> originalList = ArrayListData.originalList1;
        INSTANCE.sort(originalList);
        INSTANCE.print(originalList);
    }

    @Override
    public void sort(List<Integer> list) {
        int size = list.size();
        int count =0;
        boolean swap = true;
        for (int i = 0; i < size; i++) {
            swap = false;
            for (int j = 0; j < size - i - 1; j++) {
                Integer a = list.get(j);
                Integer b = list.get(j + 1);
                if (a > b) {
                    list.set(j, b);
                    list.set(j + 1, a);
                    swap = true;
                }
                count++;
            }
            if(!swap){
                break;
            }
        }

        System.out.println(count);
    }

}
