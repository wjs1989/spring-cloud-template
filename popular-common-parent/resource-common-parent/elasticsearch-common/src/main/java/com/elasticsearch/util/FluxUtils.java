package com.elasticsearch.util;

import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;

/**
 * @author wenjs
 */
public class FluxUtils {
    public FluxUtils() {
    }

    public static <T> Flux<List<T>> bufferRate(Flux<T> flux, int rate, Duration maxTimeout) {
        return bufferRate(flux, rate, 100, maxTimeout);
    }

    public static <T> Flux<List<T>> bufferRate(Flux<T> flux, int rate, int maxSize, Duration maxTimeout) {
        return Flux.create((sink) -> {
            FluxUtils.BufferRateSubscriber<T> subscriber = new FluxUtils.BufferRateSubscriber(sink, maxSize, rate, maxTimeout, (e, arr) -> {
                return ((List)arr).size() >= maxSize;
            });
            flux.elapsed().subscribe(subscriber);
            sink.onDispose(subscriber);
        });
    }

    public static <T> Flux<List<T>> bufferRate(Flux<T> flux, int rate, int maxSize, Duration maxTimeout, BiPredicate<T, List<T>> flushCondition) {
        return Flux.create((sink) -> {
            FluxUtils.BufferRateSubscriber<T> subscriber = new FluxUtils.BufferRateSubscriber(sink, maxSize, rate, maxTimeout, (e, arr) -> {
                return flushCondition.test((T) e, (List)arr) || ((List)arr).size() >= maxSize;
            });
            flux.elapsed().subscribe(subscriber);
            sink.onDispose(subscriber);
        });
    }

    static class BufferRateSubscriber<T> extends BaseSubscriber<Tuple2<Long, T>> {
        int bufferSize;
        int rate;
        volatile List<T> bufferArray;
        FluxSink<List<T>> sink;
        Duration timeout;
        Scheduler timer = Schedulers.parallel();
        Disposable timerDispose;
        private final BiPredicate<T, List<T>> flushCondition;

        BufferRateSubscriber(FluxSink<List<T>> sink, int bufferSize, int rate, Duration timeout, BiPredicate<T, List<T>> flushCondition) {
            this.sink = sink;
            this.bufferSize = bufferSize;
            this.rate = rate;
            this.timeout = timeout;
            this.flushCondition = flushCondition;
            this.newBuffer();
        }

        protected List<T> newBuffer() {
            List<T> buffer = this.bufferArray;
            this.bufferArray = new ArrayList(this.bufferSize);
            return buffer;
        }

        @Override
        protected void hookFinally(SignalType type) {
            this.doFlush();
        }

        void doFlush() {
            if (this.bufferArray.size() > 0) {
                this.sink.next(this.newBuffer());
            }

            this.request((long)this.bufferSize);
            if (this.timerDispose != null && !this.timerDispose.isDisposed()) {
                this.timerDispose.dispose();
            }

        }

        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            this.request((long)this.bufferSize);
        }

        @Override
        protected void hookOnNext(Tuple2<Long, T> value) {
            this.bufferArray.add(value.getT2());
            if ((Long)value.getT1() > (long)this.rate) {
                this.doFlush();
            } else if (this.flushCondition.test(value.getT2(), this.bufferArray)) {
                this.doFlush();
            } else if (this.timerDispose == null || this.timerDispose.isDisposed()) {
                this.timerDispose = this.timer.schedule(this::doFlush, this.timeout.toMillis(), TimeUnit.MILLISECONDS);
            }

        }
    }
}
