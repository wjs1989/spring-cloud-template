package com.elasticsearch.util;

import com.elasticsearch.core.metadata.DataType;
import com.elasticsearch.core.metadata.PropertyMetadata;
import com.elasticsearch.index.ElasticSearchIndexMetadata;
import com.elasticsearch.model.params.Term;
import com.elasticsearch.param.QueryParam;
import com.elasticsearch.param.Sort;
import com.elasticsearch.parser.DefaultLinkTypeParser;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author wenjs
 */
public class QueryParamTranslator {
    static DefaultLinkTypeParser linkTypeParser = new DefaultLinkTypeParser();

    static Consumer<Term> doNotingParamConverter = (term -> {
    });

    static Map<String, BiConsumer<DataType, Term>> converter = new ConcurrentHashMap<>();

    static BiConsumer<DataType, Term> defaultDataTypeConverter = (type, term) -> {

    };

    public static QueryBuilder createQueryBuilder(QueryParam queryParam, ElasticSearchIndexMetadata metadata) {
        BoolQueryBuilder queryBuilders = QueryBuilders.boolQuery();
        Consumer<Term> paramConverter = doNotingParamConverter;
        if (metadata != null) {
            paramConverter = t -> {
                if (org.springframework.util.StringUtils.isEmpty(t.getColumn())) {
                    return;
                }
                PropertyMetadata property = metadata.getProperty(t.getColumn());
                if (null != property) {
                    DataType type = property.getValueType();
                    converter.getOrDefault(type.getId(), defaultDataTypeConverter).accept(type, t);
                }
            };
        }
        for (Term term : queryParam.getTerms()) {
            linkTypeParser.process(term, paramConverter, queryBuilders);
        }
        return queryBuilders;
    }
    public static SearchSourceBuilder convertSearchSourceBuilder(QueryParam queryParam, ElasticSearchIndexMetadata metadata) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        if (queryParam.isPaging()) {
            sourceBuilder.from(queryParam.getPageIndex() * queryParam.getPageSize());
            sourceBuilder.size(queryParam.getPageSize());
        }
        for (Sort sort : queryParam.getSorts()) {
            if (!StringUtils.isEmpty(sort.getName())) {
                sourceBuilder.sort(sort.getName(), SortOrder.fromString(sort.getOrder()));
            }
        }

        return sourceBuilder.query(createQueryBuilder(queryParam,metadata));
    }
}
