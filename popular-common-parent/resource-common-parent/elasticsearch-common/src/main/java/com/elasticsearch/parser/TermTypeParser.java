package com.elasticsearch.parser;

import com.elasticsearch.model.params.Term;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @version 1.0
 **/
public interface TermTypeParser {

    void process(Supplier<Term> termSupplier, Function<QueryBuilder, BoolQueryBuilder> function);

}
