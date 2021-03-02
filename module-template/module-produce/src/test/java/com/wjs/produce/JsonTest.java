package com.wjs.produce;

import cn.hutool.core.date.DateUnit;
import com.alibaba.fastjson.JSONObject;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

//@Slf4j(topic = "wenjs")
@ToString
public class JsonTest {
    int a;
    String b;

    public static void main(String[] args) throws UnsupportedEncodingException {

        //  ExecutorService executorService = Executors.newFixedThreadPool(1);

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>()) {
//
//            protected void beforeExecute(Thread t, Runnable r) {
//                System.out.println("------beforeExecute-------");
//            }
//            protected void afterExecute(Thread t, Runnable r) {
//                System.out.println("------afterExecute-------");
//            }
//        };
//
//        threadPoolExecutor.execute(()->{
//            System.out.println("——————————————execute————————————");
//        });


//        String json = "{\"activityName\":\"湖口一小护校队申请报名活动名称显示区域活动名称显示区域\",\"address\":\"XXXXX活动详细地址\",\"areaList\":[{\"activityId\":\"2\",\"area\":\"湖口县\",\"community\":\"乡2\",\"id\":\"2\",\"town\":\"镇2\"},{\"activityId\":\"2\",\"area\":\"xxx乡镇1\",\"community\":\"xxx街道社区1\",\"town\":\"湖口县\"}],\"beginTime\":\"2020-11-29 06:00\",\"birthdate\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.\",\"deadlineTime\":\"2020-12-31 05:07\",\"endTime\":\"2010-11-30 20:00\",\"file\":\"333.jpg\",\"fileUrl\":\"/dev-imgApi/base/skipToken/image/imagePreview/?originalFilename=333.jpg\",\"id\":\"2\",\"note\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.\",\"personCount\":100,\"personCountTotal\":0,\"stateName\":\"\",\"type\":\"防汛\"}";
//
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        System.out.println(jsonObject.toString());
//        JSONObject.parseObject(json, ActivityManageUpdateReq.class);
//        System.out.println(json);
//        String json1="{\"note\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.\",\"deadlineTime\":\"2020-12-31 05:07\",\"address\":\"XXXXX活动详细地址\",\"birthdate\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.\",\"personCountTotal\":0,\"areaList\":[{\"area\":\"湖口县\",\"activityId\":\"2\",\"town\":\"镇2\",\"id\":\"2\",\"community\":\"乡2\"},{\"area\":\"xxx乡镇1\",\"activityId\":\"2\",\"town\":\"湖口县\",\"community\":\"xxx街道社区1\"}],\"activityName\":\"湖口一小护校队申请报名活动名称显示区域活动名称显示区域\",\"type\":\"防汛\",\"personCount\":100,\"file\":\"333.jpg\",\"stateName\":\"\",\"fileUrl\":\"/dev-imgApi/base/skipToken/image/imagePreview/?originalFilename=333.jpg\",\"beginTime\":\"2020-11-29 06:00\",\"endTime\":\"2010-11-30 20:00\",\"id\":\"2\"}";
//        JSONObject.parseObject(json1, ActivityManageUpdateReq.class);

        //<editor-fold desc="新增加">
         List<String> list = new ArrayList<>();
         list.add("1");
         list.add("2");

        final Iterator<String> iterator = list.iterator();
        //</editor-fold>

//
//        list.stream().map(p -> {
//            return p.toString();
//        }).forEach(o -> {
//            o.toString();
//        });
//
//        list.stream().collect(Collectors.toList());
//
//        String max = Collections.max(list);
//
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                int subLength = o1.length() - o2.length();
//                return subLength == 0 ? o1.compareTo(o2) : subLength;
//            }
//        });
//        Collections.sort(list, Comparator.comparingInt(String::length).thenComparing(o -> o));
//
//        Collections.sort(list, Comparator.comparing(o -> o));
//        log.info("a");

//
//        System.out.println(Math.sin( Math.toRadians(30)));
//        System.out.println(Math.cos(Math.toRadians(30)));


        // Logger log = new Logger();
        // log.info("打印一条消息");



    }


    public static class Logger {
        String logData;
        public void info(String message) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss,SSS");
            String time = simpleDateFormat.format(new Date());
            System.out.println(String.format("%s |-INFO in %s ---> %s", time, printLine(), message));
        }

        public String printLine() {
            try {
                throw new Exception();
            } catch (Exception e) {
               // final String[] logData = {""};

                e.printStackTrace(new PrintStream(new OutputStream() {
                    byte[] src = new byte[1024];
                    int i = 0;

                    @Override
                    public void write(int b) throws IOException {
                        src[i++] = (byte) b;
                    }

                    public void flush() throws IOException {
                        logData = new String(src, "UTF-8");
                    }
                }, true));

                String[] split = logData.trim().split("\r\n");
                String stack = split[split.length - 1];
                String at = stack.substring(stack.indexOf("at") + 2, stack.indexOf("("));
                String line = stack.substring(stack.lastIndexOf(":"), stack.length() - 1);

                return at + line;
            }
        }
    }

}
