package com.wjs.elasticsearch.elastic.params;

import java.util.List;

/**
 * @author wenjs
 */
public interface Page<T> {
    long getTotal();
    List<T> getData();
    int getPage();
    int getSize();
}
