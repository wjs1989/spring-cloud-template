package com.elasticsearch;

import com.elasticsearch.index.DefaultElasticSearchIndexManager;
import com.elasticsearch.index.ElasticSearchIndexManager;
import com.elasticsearch.index.ElasticSearchIndexProperties;
import com.elasticsearch.index.ElasticSearchIndexStrategy;
import com.elasticsearch.index.strategies.DirectElasticSearchIndexStrategy;
import com.elasticsearch.index.strategies.TimeByMonthElasticSearchIndexStrategy;
import com.elasticsearch.service.reactive.DefaultReactiveElasticsearchClient;
import com.elasticsearch.service.reactive.ReactiveElasticsearchClient;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@Configurable
@ComponentScan
public class ElasticsearchAutoConfiguration {

    /**
     * 索引策略管理器
     * @param strategies
     * @return
     */
    @Bean
    public ElasticSearchIndexManager elasticSearchIndexManager(List<ElasticSearchIndexStrategy> strategies){

        return new DefaultElasticSearchIndexManager(strategies);
    }

    /**
     * 索引策略 直接存
     * @param client
     * @param properties
     * @return
     */
    @Bean
    public DirectElasticSearchIndexStrategy directElasticSearchIndexStrategy(ReactiveElasticsearchClient client, ElasticSearchIndexProperties properties){
        return new DirectElasticSearchIndexStrategy(client,properties);
    }

    /**
     * 索引策略  按月
     * @param client
     * @param properties
     * @return
     */
    @Bean
    public TimeByMonthElasticSearchIndexStrategy timeByMonthElasticSearchIndexStrategy(ReactiveElasticsearchClient client, ElasticSearchIndexProperties properties){
        return new TimeByMonthElasticSearchIndexStrategy(client,properties);
    }

}
