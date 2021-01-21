package com.wjs.produce.sort;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public abstract class BaseSort {
    public abstract void sort(List<Integer> list);


    public void print(List<Integer> list) {

        if (!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(p -> {
                System.out.print(p + " ");
            });
        }
        System.out.println();
    }

}
