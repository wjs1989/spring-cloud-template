package com.wjs.produce.proxy;


public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {


    public static boolean isEmpty(Object[] array) {
        return !isNotEmpty(array);
    }

    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }
}
