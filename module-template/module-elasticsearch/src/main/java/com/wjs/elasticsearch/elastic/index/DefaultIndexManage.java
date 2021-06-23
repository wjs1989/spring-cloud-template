package com.wjs.elasticsearch.elastic.index;

import com.wjs.elasticsearch.elastic.annotations.EsDocument;
import com.wjs.elasticsearch.elastic.index.strategy.DirectElasticSearchIndexStrategy;
import com.wjs.elasticsearch.elastic.index.strategy.ElasticSearchIndexStrategy;
import com.wjs.elasticsearch.elastic.index.strategy.MonthElasticSearchIndexStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenjs
 */
public class DefaultIndexManage implements IndexManage {

    public HashMap<Class, IndexDetial> indexClassMap = new HashMap<>();

    public HashMap<String, IndexDetial> indexStringMap = new HashMap<>();

    public HashMap<String, ElasticSearchIndexStrategy> indexStrategy = new HashMap<>();

    public String wrapIndex(String index) {
        return index.toLowerCase();
    }

    private IndexDetial getIndex(Class clasz) {
        IndexDetial index = indexClassMap.get(clasz);
        if (Objects.isNull(index)) {
            EsDocument annotation = (EsDocument) clasz.getDeclaredAnnotation(EsDocument.class);
            if (annotation == null) {
                throw new RuntimeException(String.format("class name: %s can not find Annotation [EsDocument], please check", clasz.getName()));
            }

            index = new IndexDetial(annotation.strategy(), wrapIndex(annotation.index()));
            indexClassMap.put(clasz, index);
            indexStringMap.put(index.getIndex(), index);
        }

        return index;
    }

    @Override
    public String getIndexForSearch(String index) {
        IndexDetial indexDetial = indexStringMap.get(index);
        return getElasticSearchIndexStrategy(indexDetial.getStrategy())
                .getIndexForSearch(indexDetial.getIndex());
    }

    @Override
    public String getIndexForSave(String index) {
        IndexDetial indexDetial = indexStringMap.get(index);
        return getElasticSearchIndexStrategy(indexDetial.getStrategy())
                .getIndexForSave(indexDetial.getIndex());
    }

    @Override
    public String getIndexForSearch(Class clasz) {
        IndexDetial index = getIndex(clasz);
        return getElasticSearchIndexStrategy(index.getStrategy()).getIndexForSearch(index.getIndex());
    }

    @Override
    public String getIndexForSave(Class clasz) {
        IndexDetial index = getIndex(clasz);
        return getElasticSearchIndexStrategy(index.getStrategy()).getIndexForSave(index.getIndex());
    }

    ElasticSearchIndexStrategy getElasticSearchIndexStrategy(String strategy) {

        return indexStrategy.getOrDefault(strategy, indexStrategy.get("direct"));
    }


    static class IndexDetial {
        private String index;
        private String strategy;

        public IndexDetial(String strategy, String index) {
            this.strategy = strategy;
            this.index = index;
        }

        public String getStrategy() {
            return strategy;
        }

        public String getIndex() {
            return index;
        }
    }

    public DefaultIndexManage() {
        indexStrategy.put("direct", new DirectElasticSearchIndexStrategy());
        indexStrategy.put("month", new MonthElasticSearchIndexStrategy());
    }
}
