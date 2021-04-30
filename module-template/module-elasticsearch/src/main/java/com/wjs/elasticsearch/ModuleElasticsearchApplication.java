package com.wjs.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.wjs.elasticsearch.elastic.DefaultElasticsearchService;
import com.wjs.elasticsearch.logger.model.SerializableSystemLog;
import com.wjs.elasticsearch.model.BusinessLog;
import com.wjs.elasticsearch.model.ElasticEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import java.util.Date;

@SpringBootApplication
public class ModuleElasticsearchApplication {

    @Autowired
    private DefaultElasticsearchService elasticsearchService;

    public static void main(String[] args) {
        SpringApplication.run(ModuleElasticsearchApplication.class, args);
    }


    @EventListener
    public void eventListener(ElasticEntity log) throws Exception {
        elasticsearchService.save(log);
    }
    @EventListener
    public void eventListener(SerializableSystemLog log) throws Exception {

        System.out.println("日志信息"+ JSONObject.toJSONString(log));
    }

}
