package com.wjs.elasticsearch.elastic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wjs.elasticsearch.elastic.annotations.EsDocument;
import com.wjs.elasticsearch.elastic.annotations.EsField;
import com.wjs.elasticsearch.elastic.annotations.EsId;
import com.wjs.elasticsearch.elastic.config.ElasticSearchIndexProperties;
import com.wjs.elasticsearch.elastic.enums.FieldType;
import com.wjs.elasticsearch.elastic.index.DefaultIndexManage;
import com.wjs.elasticsearch.elastic.index.IndexManage;
import com.wjs.elasticsearch.elastic.params.Page;
import com.wjs.elasticsearch.elastic.params.PageImpl;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.unit.DataSize;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * es 操作工具类;
 * 这里均采用同步调用的方式
 *
 * @author wenjs
 */
public class DefaultElasticsearchService implements ElasticsearchService {

    private Logger log = LoggerFactory.getLogger(DefaultElasticsearchService.class);

    /**
     * 保存类名与类信息
     */
    private Map<String, Class<?>> classCache = new ConcurrentHashMap();

    private final IndexManage indexManage = new DefaultIndexManage();

    private ElasticSearchIndexProperties properties;
    private RestHighLevelClient restHighLevelClient;

    public DefaultElasticsearchService(RestHighLevelClient restHighLevelClient, ElasticSearchIndexProperties properties) {
        this.restHighLevelClient = restHighLevelClient;
        this.properties = properties;
    }

    public String getAlias(String index) {
        return index.concat("_alias");
    }

    /**
     * 创建索引(默认分片数为5和副本数为1)
     *
     * @param clazz 根据实体自动映射es索引
     * @throws IOException
     */
    public boolean createIndex(Class clazz) throws Exception {

        CreateIndexRequest request = new CreateIndexRequest(indexManage.getIndexForSave(clazz));

        // 设置分片数,副本数
        buildSetting(request);

        request.mapping(generateBuilder(clazz));
        String index = indexManage.getIndexForSearch(clazz);
        request.alias(new Alias(index));
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        // 指示是否所有节点都已确认请求
        boolean acknowledged = response.isAcknowledged();
        // 指示是否在超时之前为索引中的每个分片启动了必需的分片副本数
        boolean shardsAcknowledged = response.isShardsAcknowledged();
        if (acknowledged || shardsAcknowledged) {
            log.info("创建索引成功！索引名称为{}", index);
            return true;
        }
        return false;
    }

    /**
     * 创建索引(默认分片数为5和副本数为1)
     *
     * @param clazz 根据实体自动映射es索引
     * @throws IOException
     */
    public boolean createIndexIfNotExist(Class clazz) throws Exception {
        if (!isIndexExists(indexManage.getIndexForSave(clazz))) {
            return createIndex(clazz);
        }
        return false;
    }

    /**
     * 更新索引(默认分片数为5和副本数为1)：
     * 只能给索引上添加一些不存在的字段
     * 已经存在的映射不能改
     *
     * @param clazz 根据实体自动映射es索引
     * @throws IOException
     */
    public boolean updateIndex(Class clazz) throws Exception {
        String index = indexManage.getIndexForSearch(clazz);
        PutMappingRequest request = new PutMappingRequest(index);

        request.source(generateBuilder(clazz));
        AcknowledgedResponse response = restHighLevelClient.indices().putMapping(request, RequestOptions.DEFAULT);
        // 指示是否所有节点都已确认请求
        boolean acknowledged = response.isAcknowledged();

        if (acknowledged) {
            log.info("更新索引索引成功！索引名称为{}", index);
            return true;
        }
        return false;
    }

    /**
     * 删除索引
     *
     * @param indexName
     * @return
     */
    public boolean delIndex(String indexName) {
        boolean acknowledged = false;
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            deleteIndexRequest.indicesOptions(IndicesOptions.LENIENT_EXPAND_OPEN);
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            acknowledged = delete.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acknowledged;
    }

    /**
     * 判断索引是否存在
     *
     * @param indexName
     * @return
     */
    public boolean isIndexExists(String indexName) {
        boolean exists = false;
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
            getIndexRequest.humanReadable(true);
            exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }


    /**
     * @return
     */
    public IndexResponse save(Object o) throws Exception {
        EsDocument esDocument = getEsDocument(o);
        String indexName = esDocument.index();

        IndexRequest request = new IndexRequest(indexManage.getIndexForSave(o.getClass()));
        Field esId = getFieldByAnnotation(o, EsId.class);
        if (esId != null) {
            esId.setAccessible(true);
            try {
                Object id = esId.get(o);
                request = request.id(id.toString());
            } catch (IllegalAccessException e) {
                log.error("获取id字段出错：{}", e);
            }
        }

        String json = JSON.toJSONStringWithDateFormat(o, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
        setDebugLogger(json);
        request.source(json, XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        return indexResponse;
    }


    /**
     * 根据id查询
     *
     * @return
     */
    public String queryById(String indexName, String id) throws IOException {
        GetRequest getRequest = new GetRequest(indexName, id);
        // getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);

        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        String jsonStr = getResponse.getSourceAsString();
        return jsonStr;
    }

    /**
     * 查询封装返回json字符串
     *
     * @param indexName
     * @param searchSourceBuilder
     * @return
     * @throws IOException
     */
    public String search(String indexName, SearchSourceBuilder searchSourceBuilder) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexManage.getIndexForSearch(indexName));
        searchRequest.source(searchSourceBuilder);
        //  searchRequest.scroll(TimeValue.timeValueMinutes(1L));
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHits hits = searchResponse.getHits();
        JSONArray jsonArray = new JSONArray();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            JSONObject jsonObject = JSON.parseObject(sourceAsString);
            jsonArray.add(jsonObject);
        }
        setDebugLogger("返回总数为：" + hits.getTotalHits());
        return jsonArray.toJSONString();
    }

    public <T> Page<T> search(String indexName, SearchSourceBuilder searchSourceBuilder, Class<T> s) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);
        //  searchRequest.scroll(TimeValue.timeValueMinutes(1L));
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHits hits = searchResponse.getHits();
        JSONArray jsonArray = new JSONArray();

        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            JSONObject jsonObject = JSON.parseObject(sourceAsString);
            jsonArray.add(jsonObject);
        }
        log.info("返回总数为：" + hits.getTotalHits());
        int total = (int) hits.getTotalHits().value;

        // 封装分页
        List<T> list = jsonArray.toJavaList(s);

        return PageImpl.of(total, searchSourceBuilder.from(), searchSourceBuilder.size(), list);
    }

    /**
     * 查询封装，返回集合
     *
     * @param searchSourceBuilder
     * @param s
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> List<T> search(SearchSourceBuilder searchSourceBuilder, Class<T> s) throws Exception {
        EsDocument declaredAnnotation = (EsDocument) s.getDeclaredAnnotation(EsDocument.class);
        if (declaredAnnotation == null) {
            throw new Exception(String.format("class name: %s can not find Annotation [EsDocument], please check", s.getName()));
        }
        String indexName = declaredAnnotation.index();
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHits hits = searchResponse.getHits();
        JSONArray jsonArray = new JSONArray();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            JSONObject jsonObject = JSON.parseObject(sourceAsString);
            jsonArray.add(jsonObject);
        }
        // 封装分页
        List<T> list = jsonArray.toJavaList(s);
        return list;
    }


    /**
     * 批量插入文档
     * 文档存在 则插入
     * 文档不存在 则更新
     *
     * @param list
     * @return
     */
    public <T> boolean batchSaveOrUpdate(List<T> list) throws Exception {
        Object o1 = list.get(0);
        EsDocument declaredAnnotation = (EsDocument) o1.getClass().getDeclaredAnnotation(EsDocument.class);
        if (declaredAnnotation == null) {
            throw new Exception(String.format("class name: %s can not find Annotation [@EsDocument], please check", o1.getClass().getName()));
        }
        String indexName = declaredAnnotation.index();

        BulkRequest request = new BulkRequest(indexName);
        for (Object o : list) {
            String jsonStr = JSON.toJSONString(o);
            IndexRequest indexReq = new IndexRequest().source(jsonStr, XContentType.JSON);

            Field fieldByAnnotation = getFieldByAnnotation(o, EsId.class);
            if (fieldByAnnotation != null) {
                fieldByAnnotation.setAccessible(true);
                try {
                    Object id = fieldByAnnotation.get(o);
                    indexReq = indexReq.id(id.toString());
                } catch (IllegalAccessException e) {
                    log.error("获取id字段出错：{}", e);
                }
            }
            request.add(indexReq);
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);

        for (BulkItemResponse bulkItemResponse : bulkResponse) {
            DocWriteResponse itemResponse = bulkItemResponse.getResponse();
            IndexResponse indexResponse = (IndexResponse) itemResponse;
            log.info("单条返回结果：{}", indexResponse);
            if (bulkItemResponse.isFailed()) {
                log.error("es 返回错误{}", bulkItemResponse.getFailureMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * 删除文档
     *
     * @param indexName： 索引名称
     * @param docId：     文档id
     */
    public boolean deleteDoc(String indexName, String docId) throws IOException {
        DeleteRequest request = new DeleteRequest(indexName, docId);
        DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        // 解析response
        String index = deleteResponse.getIndex();
        String id = deleteResponse.getId();
        long version = deleteResponse.getVersion();
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                log.info("删除失败，原因为 {}", reason);
            }
        }
        return true;
    }

    /**
     * 根据json类型更新文档
     *
     * @param indexName
     * @param docId
     * @param o
     * @return
     * @throws IOException
     */
    public boolean updateDoc(String indexName, String docId, Object o) throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, docId);
        request.doc(JSON.toJSONString(o), XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        String index = updateResponse.getIndex();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            return true;
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            return true;
        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {

        }
        return false;
    }

    /**
     * 根据Map类型更新文档
     *
     * @param indexName
     * @param docId
     * @param map
     * @return
     * @throws IOException
     */
    public boolean updateDoc(String indexName, String docId, Map<String, Object> map) throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, docId);
        request.doc(map);
        UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        String index = updateResponse.getIndex();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            return true;
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            return true;
        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {

        }
        return false;
    }


    public XContentBuilder generateBuilder(Class clazz) throws IOException {
        // 获取索引名称及类型
        EsDocument doc = (EsDocument) clazz.getAnnotation(EsDocument.class);
        if (log.isDebugEnabled()) {
            log.debug("class {} set index:{},type:{}", clazz.getSimpleName(), doc.index(), doc.type());
        }

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.startObject("properties");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            if (f.isAnnotationPresent(EsField.class)) {
                // 获取注解
                EsField esFieldAnnotation = f.getDeclaredAnnotation(EsField.class);

                // 如果嵌套对象：
                /**
                 * {
                 *   "mappings": {
                 *     "properties": {
                 *       "region": {
                 *         "type": "keyword"
                 *       },
                 *       "manager": {
                 *         "properties": {
                 *           "age":  { "type": "integer" },
                 *           "name": {
                 *             "properties": {
                 *               "first": { "type": "text" },
                 *               "last":  { "type": "text" }
                 *             }
                 *           }
                 *         }
                 *       }
                 *     }
                 *   }
                 * }
                 */
                if (esFieldAnnotation.type() == FieldType.OBJECT) {
                    // 获取当前类的对象-- Action
                    Class<?> type = f.getType();
                    Field[] df2 = type.getDeclaredFields();
                    builder.startObject(f.getName());
                    builder.startObject("properties");
                    // 遍历该对象中的所有属性
                    for (Field f2 : df2) {
                        if (f2.isAnnotationPresent(EsField.class)) {
                            // 获取注解
                            EsField declaredAnnotation2 = f2.getDeclaredAnnotation(EsField.class);
                            builder.startObject(f2.getName());
                            builder.field("type", declaredAnnotation2.type().getType());
                            // keyword不需要分词
                            if (declaredAnnotation2.type() == FieldType.TEXT) {
                                builder.field("analyzer", declaredAnnotation2.analyzer().getType());
                            }
                            if (declaredAnnotation2.type() == FieldType.DATE) {
                                builder.field("format", "yyyy-MM-dd HH:mm:ss");
                            }
                            builder.endObject();
                        }
                    }
                    builder.endObject();
                    builder.endObject();

                } else {
                    builder.startObject(f.getName());
                    builder.field("type", esFieldAnnotation.type().getType());
                    // keyword不需要分词
                    if (esFieldAnnotation.type() == FieldType.TEXT) {
                        builder.field("analyzer", esFieldAnnotation.analyzer().getType());
                    }
                    if (esFieldAnnotation.type() == FieldType.DATE) {
                        builder.field("format", "yyyy-MM-dd HH:mm:ss");
                    }
                    builder.endObject();
                }
            }
        }
        // 对应property
        builder.endObject();
        builder.endObject();
        return builder;
    }


    /**
     * 获取标注了某个注解的字段 首个
     *
     * @param o
     * @param annotationClass
     * @return
     */
    public Field getFieldByAnnotation(Object o, Class annotationClass) {
        Class<?> clazz = o.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            for (Field f : declaredFields) {
                if (f.isAnnotationPresent(annotationClass)) {
                    return f;
                }
            }
        }
        return null;
    }


    /**
     * 设置分片
     *
     * @param request
     */
    public void buildSetting(CreateIndexRequest request) {
        request.settings(properties.toSettings());
    }


    private Class getClassByName(String className) {
        Class<?> clazz = classCache.get(className);
        if (Objects.isNull(clazz)) {
            try {
                classCache.put(className, Class.forName(className));
            } catch (ClassNotFoundException e) {
            }
        }
        return classCache.get(className);
    }

    private EsDocument getEsDocument(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        EsDocument esDocument = clazz.getDeclaredAnnotation(EsDocument.class);
        if (esDocument == null) {
            throw new Exception(String.format("class name: %s can not find Annotation [EsDocument], please check", obj.getClass().getName()));
        }
        return esDocument;
    }

    /**
     * 记录调试日志
     *
     * @param msg
     */
    private void setDebugLogger(String msg) {
        if (log.isDebugEnabled()) {
            log.debug(msg);
        }
    }


}
