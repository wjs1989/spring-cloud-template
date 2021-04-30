package com.elasticsearch.index;

/**
 * @author wenjs
 */
public enum LoggerIndexProvider implements ElasticIndex {

    ACCESS("access_logger"),
    SYSTEM("system_logger");

    private String index;

    private LoggerIndexProvider(String index) {
        this.index = index;
    }

    @Override
    public String getIndex() {
        return null;
    }
}
