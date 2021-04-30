package com.elasticsearch.server;

import org.elasticsearch.node.NodeValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModuleElasticsearchServerApplication {

    public static void main(String[] args) throws NodeValidationException {
        SpringApplication.run(ModuleElasticsearchServerApplication.class, args);

        new EmbeddedElasticSearch(new EmbeddedElasticSearchProperties()).start();
    }

}
