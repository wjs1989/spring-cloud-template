package com.wjs.elasticsearch.elastic.config;

import org.elasticsearch.common.settings.Settings;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wenjs
 */
@ConfigurationProperties(prefix = "spring.elasticsearch.index.settings")
public class ElasticSearchIndexProperties {

    private int numberOfShards = 1;

    private int numberOfReplicas = 0;

    public Settings toSettings() {

        return Settings.builder()
                .put("number_of_shards", Math.max(1, numberOfShards))
                .put("number_of_replicas", numberOfReplicas)
                .build();
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
