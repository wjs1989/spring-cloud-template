package com.wjs.elasticsearch.elastic.config;

import com.wjs.elasticsearch.elastic.DefaultElasticsearchService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wenjs
 */
@Configuration
@EnableConfigurationProperties({ElasticSearchIndexProperties.class})
public class ElasticSarchAutoConfiguration {


    @Bean
    public DefaultElasticsearchService elasticsearchService(RestHighLevelClient restHighLevelClient, ElasticSearchIndexProperties properties) {
        return new DefaultElasticsearchService(restHighLevelClient, properties);
    }

}
