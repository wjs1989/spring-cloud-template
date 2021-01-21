package com.wjs.produce.sort;

import java.util.List;

/**
 * 快速排序
 * 时间复杂度 O(n*log n)
 */
public class QuickSort extends BaseSort {
    static QuickSort INSTANCE = new QuickSort();


    public static void main(String[] args) {
        List<Integer> originalList = ArrayListData.originalList2;

        INSTANCE.print(originalList);
        INSTANCE.sort(originalList);
        INSTANCE.print(originalList);
    }

    /**
     * 获取一个基准数，把序列比基准数大的放一边，比基准数小的放一边
     *
     * @param list
     */
    @Override
    public void sort(List<Integer> list) {
        qSort(list, 0, list.size() - 1);
    }

    private void qSort(List<Integer> list, int begin, int end) {
        if (begin < end) {
            int pivot = partition(list, begin, end);
            if (pivot > begin) {
                qSort(list, begin, pivot - 1);
            }
            if (pivot < end) {
                qSort(list, pivot + 1, end);
            }
        }

    }


    private int partition(List<Integer> list, int begin, int end) {

        int pivotKey = list.get(begin);
        while (begin < end) {
            while (begin < end && pivotKey <= list.get(end)) {
                end--;
            }
            swap(list, begin, end);

            while (begin < end && pivotKey >= list.get(begin)) {
                begin++;
            }
            swap(list, begin, end);
        }
        return begin;
    }


    /**
     * 交换数据
     *
     * @param list
     * @param a    位置a
     * @param b    位置b
     */
    public void swap(List<Integer> list, int a, int b) {
        int node = list.get(a);
        list.set(a, list.get(b));
        list.set(b, node);

        INSTANCE.print(list);
    }

}
