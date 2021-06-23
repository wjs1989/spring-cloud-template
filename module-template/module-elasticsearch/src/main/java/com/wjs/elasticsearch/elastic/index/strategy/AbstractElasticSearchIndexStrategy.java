package com.wjs.elasticsearch.elastic.index.strategy;

/**
 * @author wenjs
 */
public abstract class AbstractElasticSearchIndexStrategy implements ElasticSearchIndexStrategy {

    public String wrapIndex(String index) {
        return index.toLowerCase();
    }

    public String getAlias(String index) {
        return wrapIndex(index).concat("_alias");
    }
}
