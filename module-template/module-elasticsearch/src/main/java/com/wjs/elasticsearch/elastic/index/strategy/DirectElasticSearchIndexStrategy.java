package com.wjs.elasticsearch.elastic.index.strategy;

/**
 * @author wenjs
 */
public class DirectElasticSearchIndexStrategy extends AbstractElasticSearchIndexStrategy{

    @Override
    public String getId() {
        return "direct";
    }

    @Override
    public String getIndexForSave(String index) {
        return wrapIndex(index);
    }

    @Override
    public String getIndexForSearch(String index) {
        return getAlias(index);
    }
}
