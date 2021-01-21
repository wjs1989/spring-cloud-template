package com.wjs.produce.sort;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 直接插入排序
 * 时间复杂度 O(n²)
 */
public class StraightInsertionSort extends BaseSort {
    static StraightInsertionSort INSTANCE = new StraightInsertionSort();


    public static void main(String[] args) {
        List<Integer> originalList = ArrayListData.originalList;
        INSTANCE.print(originalList);
        INSTANCE.sort(originalList);
        INSTANCE.print(originalList);
    }

    @Override
    public void sort(List<Integer> list) {

        for (int i = 1; i < list.size(); i++) {

            int tmp = list.get(i);
            int preIndex = i-1; // 初始比较索引
            while (preIndex >=0 && list.get(preIndex)>tmp)
            {
                Integer value = list.get(preIndex);
                list.set(preIndex + 1, value);
                preIndex--;
            }
            list.set(preIndex+ 1, tmp);



//            int j = 0;
//            for (j = i - 1; j >= 0 && list.get(j) > tmp; j--) {
//                list.set(j + 1, list.get(j));
//            }
//            list.set(j + 1, tmp);
        }

    }
//    @Override
//    public void sort(List<Integer> list) {
//        List<Integer> newList = new ArrayList<>(list.size());
//        if (!CollectionUtils.isEmpty(list)) {
//            list.forEach(e -> insert(newList, e));
//        }
//
//        INSTANCE.print(newList);
//    }


    private void insert(List<Integer> list, Integer element) {
        if (list.size() == 0 || element >= list.get(list.size() - 1)) {
            list.add(element);
        } else if (element <= list.get(0)) {
            list.add(0, element);
        } else {
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i) >= element) {
//                    list.add(i, element);
//                    return;
//                }
//            }


            list.add(element);
            int preIndex = list.size() - 2;
            while (preIndex >= 0 && list.get(preIndex) >= element) {
                Integer value = list.get(preIndex);
                list.set(preIndex + 1, value);
                preIndex--;
            }
            list.set(preIndex + 1, element);
        }
    }


    private void insertOld2(List<Integer> list, Integer element) {

        if (list.size() == 0 || element >= list.get(list.size() - 1)) {
            list.add(element);
        } else if (element <= list.get(0)) {
            list.add(0, element);
        } else {
            int end = list.size() / 2;
            int begin = 0;
            do {
                Integer value = list.get(end);
                if (value < element) {
                    begin = end;
                    end = list.size() - 1;
                    if (begin + 5 < end && list.get(end) >= element) {
                        break;
                    }
                } else if (value > element) {
                    end = begin + (end - begin) / 2;
                    if (begin + 5 < end && list.get(end) <= element) {
                        break;
                    }
                } else {
                    list.add(end, element);
                    return;
                }
            } while (begin != end);

            for (int i = begin; i <= end + 1; i++) {
                Integer a = list.get(i);
                if (a >= element) {
                    list.add(i, element);
                    break;
                }
            }

        }
    }

    private void insertOld(List<Integer> list, Integer element) {

        if (list.size() == 0 || element >= list.get(list.size() - 1)) {
            list.add(element);
        } else if (element <= list.get(0)) {
            list.add(0, element);
        } else {
            int end = list.size() / 2;
            int begin = 0;
            Integer value = list.get(end);
            if (value < element) {
                begin = end;
                end = list.size();
            } else if (value > element) {
                // end = end / 2;
            } else {
                list.add(end, element);
            }

            for (int i = begin; i <= end + 1; i++) {
                Integer a = list.get(i);
                if (a >= element) {
                    list.add(i, element);
                    break;
                }
            }

        }
    }

}
