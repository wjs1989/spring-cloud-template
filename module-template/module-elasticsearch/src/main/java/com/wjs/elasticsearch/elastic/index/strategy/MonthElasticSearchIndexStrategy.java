package com.wjs.elasticsearch.elastic.index.strategy;

import org.springframework.format.datetime.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wenjs
 */
public class MonthElasticSearchIndexStrategy extends AbstractElasticSearchIndexStrategy {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

    @Override
    public String getId() {
        return "month";
    }

    @Override
    public String getIndexForSave(String index) {
        return wrapIndex(index).concat("_").concat(dateFormat.format(new Date()));
    }

    @Override
    public String getIndexForSearch(String index) {
        return getAlias(index);
    }

}
