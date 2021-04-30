package com.elasticsearch.index;

import org.elasticsearch.common.settings.Settings;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 索引属性
 * @author wenjs
 */
@ConfigurationProperties(prefix = "elasticsearch.index.settings")
public class ElasticSearchIndexProperties {

    private int numberOfShards = 1;

    private int numberOfReplicas = 0;

    public Settings toSettings() {

        return Settings.builder()
                .put("number_of_shards", Math.max(1, numberOfShards))
                .put("number_of_replicas", numberOfReplicas)
                .build();
    }

    public ElasticSearchIndexProperties(int numberOfShards, int numberOfReplicas) {
        this.numberOfShards = numberOfShards;
        this.numberOfReplicas = numberOfReplicas;
    }
    public ElasticSearchIndexProperties(){
    }

    public int getNumberOfShards() {
        return numberOfShards;
    }

    public void setNumberOfShards(int numberOfShards) {
        this.numberOfShards = numberOfShards;
    }

    public int getNumberOfReplicas() {
        return numberOfReplicas;
    }

    public void setNumberOfReplicas(int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
    }
}
