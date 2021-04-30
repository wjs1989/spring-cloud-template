package com.elasticsearch.parser;

import com.elasticsearch.model.params.Term;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.function.Consumer;

/**
 * @version 1.0
 **/
public interface LinkTypeParser {

    BoolQueryBuilder process(Term term, Consumer<Term> consumer, BoolQueryBuilder queryBuilders);
}
