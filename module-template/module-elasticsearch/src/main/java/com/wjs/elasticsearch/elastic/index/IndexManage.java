package com.wjs.elasticsearch.elastic.index;

/**
 * @author wenjs
 */
public interface IndexManage {
    String getIndexForSearch(String index);

    String getIndexForSave(String index);

    String getIndexForSearch(Class clasz);

    String getIndexForSave(Class clasz);

}
