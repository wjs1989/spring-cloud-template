
package com.wjs.elasticsearch.business;

import com.wjs.elasticsearch.elastic.DefaultElasticsearchService;
import com.wjs.elasticsearch.elastic.params.Page;
import com.wjs.elasticsearch.model.BusinessLog;
import com.wjs.elasticsearch.model.ElasticEntity;
import com.wjs.elasticsearch.model.SerializableAccessLog;
import com.wjs.model.util.IdGeneratorUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @author wenjs
 */
@RestController
@RequestMapping("/business")
public class BusinessController {
    Logger log = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    private DefaultElasticsearchService elasticsearchService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/create-index")
    public String createIndex() throws Exception {
        elasticsearchService.createIndex(ElasticEntity.class);
        return "ok";
    }

    @GetMapping("/add")
    public String add() throws Exception {
        ElasticEntity elasticEntity = new ElasticEntity();
        elasticEntity.setId(1234L);
        elasticEntity.setName("wenjs");
        elasticEntity.setAge(18);
        elasticEntity.setBirthday(new Date());
        elasticsearchService.save(elasticEntity);
        return "ok";
    }

    @GetMapping("/queryById")
    public String queryById(Long id) throws Exception {
        String ela_abc = elasticsearchService.queryById("ela_abc", id.toString());
        return ela_abc;
    }

    @GetMapping("/search")
    public String search() throws Exception {

        //构建查询条
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        //boolBuilder.mustNot(QueryBuilders.termQuery("age", 18));
        //  boolBuilder.must(QueryBuilders.queryStringQuery("name=wenjs"));
        // boolBuilder.must(QueryBuilders.termQuery("name", "wenjs"));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.query(boolBuilder);

        //构建返回字段
        //获取的字段（列）和不需要获取的列
        searchSourceBuilder.fetchSource(new String[]{"name", "age"}, new String[]{});

        //排序
        // FieldSortBuilder fieldSortBuilder = new FieldSortBuilder("age");
        // fieldSortBuilder = fieldSortBuilder.order(SortOrder.ASC);
        // searchSourceBuilder.sort(fieldSortBuilder);
        searchSourceBuilder.sort("age", SortOrder.ASC);


        String ela_abc = elasticsearchService.search("ela_abc", searchSourceBuilder);

        ElasticEntity elasticEntity = new ElasticEntity();
        elasticEntity.setId(IdGeneratorUtil.nextId());
        elasticEntity.setName(String.valueOf(IdGeneratorUtil.nextId()));
        elasticEntity.setAge(18);
        elasticEntity.setBirthday(new Date());
        eventPublisher.publishEvent(elasticEntity);

        return ela_abc;
    }


    @GetMapping("/search2")
    public Page<ElasticEntity> search2() throws Exception {
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.query(boolBuilder);

        Page<ElasticEntity> page = elasticsearchService.search("ela_abc", searchSourceBuilder, ElasticEntity.class);

        ElasticEntity elasticEntity = new ElasticEntity();
        elasticEntity.setId(IdGeneratorUtil.nextId());
        elasticEntity.setName(String.valueOf(IdGeneratorUtil.nextId()));
        elasticEntity.setAge(18);
        elasticEntity.setBirthday(new Date());
        eventPublisher.publishEvent(elasticEntity);
        log.info("查询数据");
        return page;
    }
}

