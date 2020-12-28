package com.wjs.elasticsearch;

import com.wjs.elasticsearch.config.BaseElasticService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ModuleElasticsearchApplicationTests {

    @Autowired
    BaseElasticService baseElasticService;

    @Test
    void contextLoads() {

        //baseElasticService.indexExist()

        System.out.println(1 << 0);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 4);
    }

}
