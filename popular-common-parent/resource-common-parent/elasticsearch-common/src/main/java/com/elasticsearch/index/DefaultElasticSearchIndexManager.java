package com.elasticsearch.index;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenjs
 */
public class DefaultElasticSearchIndexManager implements ElasticSearchIndexManager {
    private String defaultStrategy = "direct";

    private Map<String, String> indexUseStrategy = new HashMap<>();

    private final Map<String, ElasticSearchIndexStrategy> strategies = new ConcurrentHashMap<>();

    private final Map<String, ElasticSearchIndexMetadata> indexMetadataStore = new ConcurrentHashMap<>();

    public DefaultElasticSearchIndexManager(List<ElasticSearchIndexStrategy> strategies) {
        strategies.forEach(this::registerStrategy);
    }

    @Override
    public Mono<Void> putIndex(ElasticSearchIndexMetadata index) {
        return this.getIndexStrategy(index.getIndex())
                .flatMap(strategy -> strategy.putIndex(index))
                .doOnSuccess(metadata -> indexMetadataStore.put(index.getIndex(), index));
    }

    @Override
    public Mono<ElasticSearchIndexMetadata> getIndexMetadata(String index) {
        return Mono.justOrEmpty(indexMetadataStore.get(index))
                .switchIfEmpty(Mono.defer(() -> doLoadMetaData(index)
                        .doOnNext(metadata -> indexMetadataStore.put(metadata.getIndex(), metadata))));
    }

    protected Mono<ElasticSearchIndexMetadata> doLoadMetaData(String index) {
        return getIndexStrategy(index)
                .flatMap(strategy -> strategy.loadIndexMetadata(index));
    }

    @Override
    public Mono<ElasticSearchIndexStrategy> getIndexStrategy(String index) {
        return Mono.justOrEmpty(strategies.get(indexUseStrategy.getOrDefault(index.toLowerCase(), defaultStrategy)))
                .switchIfEmpty(Mono.error(() -> new IllegalArgumentException("[" + index + "] 不支持任何索引策略")));
    }

    @Override
    public void useStrategy(String index, String strategy) {
        indexUseStrategy.put(index, strategy);
    }

    public void registerStrategy(ElasticSearchIndexStrategy strategy) {
        strategies.put(strategy.getId(), strategy);
    }

}
