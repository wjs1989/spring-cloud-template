package com.elasticsearch.parser;

import com.elasticsearch.core.enums.TermTypeEnum;
import com.elasticsearch.model.params.Term;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author bsetfeng
 * @since 1.0
 **/
public class DefaultTermTypeParser implements  TermTypeParser {


    @Override
    public void process(Supplier<Term> termSupplier, Function<QueryBuilder, BoolQueryBuilder> function) {
        function.apply(queryBuilder(termSupplier.get()));
    }


    private QueryBuilder queryBuilder(Term term) {
        return TermTypeEnum.of(term.getTermType().trim()).map(e -> e.process(term)).orElse(QueryBuilders.boolQuery());
    }
}
