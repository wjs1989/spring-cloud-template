package com.elasticsearch.index.strategies;

import com.elasticsearch.core.config.DateFormatter;
import com.elasticsearch.index.ElasticSearchIndexProperties;
import com.elasticsearch.service.reactive.ReactiveElasticsearchClient;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 按月对来划分索引策略
 *
 */
public class TimeByMonthElasticSearchIndexStrategy extends TemplateElasticSearchIndexStrategy {

    private final String format = "yyyy-MM";

    public TimeByMonthElasticSearchIndexStrategy(ReactiveElasticsearchClient client, ElasticSearchIndexProperties properties) {
        super("time-by-month", client,properties);
    }

    @Override
    public String getIndexForSave(String index) {
        return wrapIndex(index).concat("_").concat(DateFormatter.toString(new Date(), format));
    }
}
