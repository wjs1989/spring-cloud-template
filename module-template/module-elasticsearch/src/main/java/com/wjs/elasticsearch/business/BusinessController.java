package com.wjs.elasticsearch.business;

import com.wjs.elasticsearch.elastic.ElasticsearchService;
import com.wjs.elasticsearch.model.ElasticEntity;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ElasticsearchService elasticsearchService;

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
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.mustNot(QueryBuilders.termQuery("age", 0));
      //  boolBuilder.must(QueryBuilders.queryStringQuery("name=wenjs"));
        boolBuilder.must(QueryBuilders.termQuery("name", "wenjs"));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.query(boolBuilder);

        FieldSortBuilder fieldSortBuilder = new FieldSortBuilder("age");
        fieldSortBuilder = fieldSortBuilder.order(SortOrder.ASC);
        searchSourceBuilder.sort(fieldSortBuilder);

        String ela_abc = elasticsearchService.search("ela_abc", searchSourceBuilder);
        return ela_abc;
    }
}
