package com.wjs.elasticsearch.elastic.index.strategy;

/**
 * @author wenjs
 */
public interface ElasticSearchIndexStrategy {
    /**
     * 策略标识
     *
     * @return ID
     */
    String getId();

    /**
     * 获取用于获取保存数据的索引
     *
     * @param index 原始索引名
     * @return 索引名
     */
    String getIndexForSave(String index);

    /**
     * 获取用于搜索的索引
     *
     * @param index 原始索引名
     * @return 索引名
     */
    String getIndexForSearch(String index);
}
