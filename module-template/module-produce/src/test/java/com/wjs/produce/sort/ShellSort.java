package com.wjs.produce.sort;

import java.util.List;

/**
 * 希尔排序
 * 时间复杂度 O(n*log n)
 */
public class ShellSort extends BaseSort {
    static ShellSort INSTANCE = new ShellSort();

    public static void main(String[] args) {
        List<Integer> originalList = ArrayListData.originalList2;

        INSTANCE.print(originalList);
        INSTANCE.sort(originalList);
        INSTANCE.print(originalList);
    }

    /**
     * 把数据list 分割成 步长为gap = size/2 的size/gap部分，分别做排序。
     * 排序完成后，重新分割成 步长为gap = gap/2 步长为size/gap部分，分别做排序。
     * 直到 gap为1.对整个数组做一次排序（此时数据已经是基本有序的了）。
     * <p>
     * 50 10 90 30 70 40 80 60 20
     * gap = size/2 = 9/4 =4
     * [50,70][10,40][90,80][30,60][20] 分组内的排序，不影响原list相对的顺序
     * 排序后得到 50 10 80 30 70 40 90 60 20
     * gap = gap/2 = 2 分2组
     * [50,90,70,80,20][10,30,40,60]
     * 排序后得到 20 10 50 30 70 40 80 60 90
     * gap = gap/2 = 2/2 =1 分1组
     * [20 10 50 30 70 40 80 60 90]
     * 排序后得到 10 20 30 40 50 60 70 80 90
     *
     * @param list
     */

    @Override
    public void sort(List<Integer> list) {
        int len = list.size();
        int gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                //gap 步长,也是分为 gap组比较 每组元素个数为 len/gap

                //插入排序
                int temp = list.get(i);
                int preIndex = i - gap;
                while (preIndex >= 0 && list.get(preIndex) > temp) {
                    list.set(preIndex + gap, list.get(preIndex));
                    preIndex -= gap;
                }
                list.set(preIndex + gap, temp);
            }
            gap /= 2;
        }

    }
    /**
    @Override
    public void sort(List<Integer> list) {
        int len = list.size();
        int gap = len / 2;
        int temp = 0;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = list.get(i);
                int preIndex = i - gap;
                while (preIndex >= 0 && list.get(preIndex) > temp) {
                    list.set(preIndex + gap, list.get(preIndex));
                    preIndex -= gap;
                }
                list.set(preIndex + gap, temp);
            }
            gap /= 2;
        }

//        while (gap > 0) {
//            for (int i = 0; i < gap; i++) {
//                for (int preIndex = i; preIndex < len / gap; preIndex += gap) {
//                    for (int j = preIndex; j < len; j += gap) {
//                        if (list.get(j + gap) < list.get(j)) {
//                            int tmp = list.get(j + gap);
//                            list.set(j + gap, list.get(j));
//                            list.set(j, tmp);
//                        }
//                    }
//                }
//            }
//            INSTANCE.print(list);
//            gap = gap / 2;
//        }
    }
     */
    /**
     @Override public void sort(List<Integer> list) {
     int len = list.size();
     int gap = len / 2;
     while (gap > 0) {
     for (int i = gap; i < len - 1; i++) {
     int tmp = list.get(i);
     int preIndex = i - gap;
     while (preIndex >= 0 && list.get(preIndex) > tmp) {
     list.set(preIndex + gap, list.get(preIndex));
     preIndex -= gap;
     }
     list.set(preIndex + gap, tmp);
     gap = gap / 2;
     }
     }
     }
     */
}
