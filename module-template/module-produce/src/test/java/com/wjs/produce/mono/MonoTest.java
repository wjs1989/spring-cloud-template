package com.wjs.produce.mono;

import com.wjs.produce.model.X;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class MonoTest {

    public static void main(String[] args) throws InterruptedException {
//        Disposable thread_run = Mono.fromRunnable(() -> {
//            System.out.println("thread run");
//
//            // throw new RuntimeException("thread run error");
//        }).subscribe(MonoTest::print, MonoTest::print);
//
//
//        Mono.fromRunnable(() -> {
//            System.out.println("thread run");
//
//            // throw new RuntimeException("thread run error");
//        });

        //    Mono.fromSupplier(() -> "Hello").subscribe(MonoTest::print);
        //  Mono.just(1).mergeWith(Flux.just(1, 2, 3)).subscribe(MonoTest::print);

        //   Mono.justOrEmpty(Optional.of("Hello")).subscribe(MonoTest::print);
//        Mono.zip(objects -> {
//            int sum = 0;
//            for (Object o:objects) {
//                sum += (Integer) o;
//            }
//
//            return sum;
//        }, Mono.just(1), Mono.just(2))
//                .subscribe(MonoTest::print);


//        Mono.zip(string -> string.length, Mono.just(1), Mono.just(1))
//                .map(MonoTest::getIntegerIntegerFunction)
//                .doOnSuccess(integer -> System.out.println("成功了"))
//                .doOnTerminate(() -> System.out.println("结束了"))
//                 //.then(Mono.just(888))
//                .subscribe(MonoTest::print);

//        Mono.defer(() -> Mono.just(88))
//                .map(integer -> integer + 1)
//                 .subscribe(MonoTest::print);
//        Mono.delay(Duration.ofMillis(3)).defer(() -> Mono.just(88))
//                 .subscribe(MonoTest::print);
//
//        System.out.println("---------- 分割线5 ----------");

//         Mono.using(() -> 1, // 1 数据源
//                 integer -> Mono.just(2 + integer), // 2 最终返回
//                 integerSource -> System.out.println("清理结果是：" + integerSource), //3 根据4执行3
//                 false)// 4 完成前调用 还是 完成后调用
//                 .flatMap(integer -> Mono.just(integer + 3))
//                 .subscribe(integer -> System.out.println("最终结果是：" + integer));


      // Mono.just(1).then(MonoTest.getIntegerIntegerFunction(2)).subscribe(integer -> System.out.println("最终结果是：" + integer));


        MonoTest test= new MonoTest();
        test.init();
        A x =  new  A("wenjs");
        test.fluxSink.next(x);
        //test.fluxSink.complete();
        test.fluxSink.next(new A("wenjs1"));
    }


   private FluxSink< A> fluxSink = null;

    private void init(){
        Flux.< A>create(sink -> fluxSink=sink).flatMap(x->{
                    System.out.println( x.getName());
                   return Mono.create(sink -> sink.success());
                } ).subscribe();
    }

    private static Mono<Integer> getIntegerIntegerFunction(Integer i) {
return Mono.just(i+10)  ;
    }

    private static void print(Object o) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println(o);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class A{
        String name;
        public A( String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
