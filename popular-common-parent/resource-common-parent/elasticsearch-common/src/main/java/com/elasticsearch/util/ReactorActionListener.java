package com.elasticsearch.util;

import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.ActionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author wenjs
 */
public class ReactorActionListener {


    public static <R, T> Mono<R> mono(Consumer<ActionListener<T>> listenerConsumer,
                                      Function<T, Mono<R>> onSuccess,
                                      Function<Exception, Mono<R>> onError) {
        return Mono.<Mono<R>>create(sink -> {
            listenerConsumer.accept(new ActionListener<T>() {
                @Override
                public void onResponse(T t) {
                    try {
                        sink.success(onSuccess.apply(t));
                    } catch (Exception e) {
                        sink.error(e);
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    try {
                        sink.success(onError.apply(e));
                    } catch (Exception e2) {
                        sink.error(e2);
                    }
                }
            });

        }).flatMap(Function.identity())
                .onErrorResume(ElasticsearchStatusException.class, e -> {
                    if (e.status().getStatus() == 404) {
                        if(e.getMessage().contains("index_not_found_exception")){
                        }else{
                        }
                        return Mono.empty();
                    }
                    return Mono.error(new Exception(e.getMessage(), e));
                });
    }


    public static <R, T> Mono<R> mono(Consumer<ActionListener<T>> listenerConsumer,
                                      Function<T, Mono<R>> onSuccess) {
        return mono(listenerConsumer, onSuccess, Mono::error);
    }

    public static <R> Mono<R> mono(Consumer<ActionListener<R>> listenerConsumer) {
        return mono(listenerConsumer, Mono::justOrEmpty, Mono::error);
    }
}
