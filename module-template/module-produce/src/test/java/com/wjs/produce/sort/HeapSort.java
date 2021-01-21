package com.wjs.produce.sort;

import java.util.List;

/**
 * 堆排序
 * 时间复杂度 O(n*log n)
 */
public class HeapSort extends BaseSort {
    static HeapSort INSTANCE = new HeapSort();


    public static void main(String[] args) {
        List<Integer> originalList = ArrayListData.originalList;

        INSTANCE.print(originalList);
        INSTANCE.sort(originalList);
        INSTANCE.print(originalList);
    }

    @Override
    public void sort(List<Integer> list) {
        int size = list.size();
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapAdust(list, i, size);
        }

        INSTANCE.print(list);
        for (int i = size - 1; i > 0; i--) {
            swap(list, 0, i);
            heapAdust(list, 0, i-1);
        }
    }

    /**
     *            50
     *        10     90
     *      30  70 40  80
     *    60  20
     *
     * 当前原始数据需要构建为最大堆，则最后一个叶子节点30 的位置
     * 就在 size/2 的位置
     */


    /**
     * 构建堆
     *
     * @param list
     * @param nodeIndex  堆节点
     * @param length       堆长度
     */
    public void heapAdust(List<Integer> list, int nodeIndex, int length) {
        //快速插入
        int node = list.get(nodeIndex);
        for (int i = 2 * nodeIndex; i <= length; i *= 2) {
            if (i < length && list.get(i) < list.get(i + 1)) {
                ++i;
            }
            if (node >= list.get(i)) {
                break;
            }
            list.set(nodeIndex, list.get(i));
            nodeIndex = i;
        }
        list.set(nodeIndex, node);
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

    }

}
