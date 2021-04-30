package com.elasticsearch.index;

/**
 * @author wenjs
 */
public interface IndexTemplateProvider {
    static String getIndexTemplate(String index) {
        return index.concat("_template");
    }
}
