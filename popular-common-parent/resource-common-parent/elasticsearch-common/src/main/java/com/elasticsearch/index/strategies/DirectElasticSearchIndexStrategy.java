package com.elasticsearch.index.strategies;

import com.elasticsearch.index.ElasticSearchIndexMetadata;
import com.elasticsearch.index.ElasticSearchIndexProperties;
import com.elasticsearch.service.reactive.ReactiveElasticsearchClient;
import reactor.core.publisher.Mono;

/**
 * 直接的
 */
public class DirectElasticSearchIndexStrategy extends AbstractElasticSearchIndexStrategy {

    public DirectElasticSearchIndexStrategy(ReactiveElasticsearchClient client, ElasticSearchIndexProperties properties) {
        super("direct", client, properties);
    }

    @Override
    public String getIndexForSave(String index) {
        return wrapIndex(index);
    }

    @Override
    public String getIndexForSearch(String index) {
        return wrapIndex(index);
    }

    @Override
    public Mono<Void> putIndex(ElasticSearchIndexMetadata metadata) {
        return doPutIndex(metadata, false);
    }

    @Override
    public Mono<ElasticSearchIndexMetadata> loadIndexMetadata(String index) {
        return doLoadIndexMetadata(index);
    }

}
