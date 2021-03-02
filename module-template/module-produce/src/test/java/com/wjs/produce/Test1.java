package com.wjs.produce;

import sun.misc.ProxyGenerator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.*;

public class Test1 {
    private static boolean stop;

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

//        Thread thread = new Thread(() -> {
//            int i = 0;
//            while (!stop) {
//                i++;
//                print();
//            }
//
//            System.out.println("结束");
//        }, "t1");
//
//        thread.start();
//
//        Thread.sleep(1000);
//        stop = true;

        // BeanDefinitionRegistryPostProcessor
//        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        ProxyGenerator.generateProxyClass(
//                "d://proxy0",new Class[]{BaseService.class}, Modifier.PUBLIC );


//        Method[] declaredMethods = DefaultBaseService1.class.getDeclaredMethods();
//        for (int i = 0; i < declaredMethods.length; i++) {
//            System.out.println(declaredMethods[i]);
//        }

//        System.out.println(getName("a"));

//        Map<Hello, String> identityHashMap2 = new IdentityHashMap<>();
//        Hello hello1 = new Hello();
//        identityHashMap2.put(hello1, "1");
//        System.out.println(identityHashMap2.get(hello1)); //1

//        Hello hello = new Hello();
//        Generic<Hello> result = new Generic<>();
//        result.setData(hello);
//
//        Hello data = result.getData();


//        String cmd ="mysqldump -h10.204.125.132 -uroot -p123456 -P36542 aa>/mysqldata/xian.sql;";
//        Runtime.getRuntime().exec(cmd);


//        ServiceLoader<BaseService1> load = ServiceLoader.load(BaseService1.class);
//        Iterator<BaseService1> iterator = load.iterator();
//        while (iterator.hasNext()) {
//            BaseService1 next = iterator.next();
//            next.doService(111);
//
//        }
       // new Thread(()->{System.out.println(1);});
       //
       //  String userHome = System.getProperty("user.dir");
       //  System.out.println(userHome);

        List<String> list = Arrays.asList("1","2","3");

        String join = String.join(",", list);
        System.out.println(join);
    }

    public static class Hello {

        public final String a = "AAA";

        static {
            System.out.println("Hello");
        }

        private Integer id;

        private String name;

        private Integer age;

        private String email;
    }

    private static void threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10,
                1L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10)) {

            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("beforeExecute->" + Thread.currentThread().getName());
            }
        };

        //threadPoolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                System.out.println("threadName->" + Thread.currentThread().getName() + finalI);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void fixed() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            System.out.println(111);
        });
    }

    public static void print() {
        // System.out.println("----------------");

//        synchronized (Test1.class){
//            int i = 0;
//        }


    }

//    void executorTest() {
//        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
//
//        scheduled.schedule(() -> {
//            System.out.println("-------------");
//        }, 5, TimeUnit.SECONDS);
//
//        scheduled.execute(new Test1()::print);
//    }


    public static <T> T getName(T t) {
        return t;
    }
}
