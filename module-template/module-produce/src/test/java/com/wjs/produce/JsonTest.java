package com.wjs.produce;

import com.alibaba.fastjson.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class JsonTest {

    public static void main(String[] args) {

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


        String json = "{\"activityName\":\"湖口一小护校队申请报名活动名称显示区域活动名称显示区域\",\"address\":\"XXXXX活动详细地址\",\"areaList\":[{\"activityId\":\"2\",\"area\":\"湖口县\",\"community\":\"乡2\",\"id\":\"2\",\"town\":\"镇2\"},{\"activityId\":\"2\",\"area\":\"xxx乡镇1\",\"community\":\"xxx街道社区1\",\"town\":\"湖口县\"}],\"beginTime\":\"2020-11-29 06:00\",\"birthdate\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.\",\"deadlineTime\":\"2020-12-31 05:07\",\"endTime\":\"2010-11-30 20:00\",\"file\":\"333.jpg\",\"fileUrl\":\"/dev-imgApi/base/skipToken/image/imagePreview/?originalFilename=333.jpg\",\"id\":\"2\",\"note\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.\",\"personCount\":100,\"personCountTotal\":0,\"stateName\":\"\",\"type\":\"防汛\"}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject.toString());
        JSONObject.parseObject(json, ActivityManageUpdateReq.class);
        System.out.println(json);
        String json1="{\"note\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.\",\"deadlineTime\":\"2020-12-31 05:07\",\"address\":\"XXXXX活动详细地址\",\"birthdate\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar sic tempor. Sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus pronin sapien nunc accuan eget.\",\"personCountTotal\":0,\"areaList\":[{\"area\":\"湖口县\",\"activityId\":\"2\",\"town\":\"镇2\",\"id\":\"2\",\"community\":\"乡2\"},{\"area\":\"xxx乡镇1\",\"activityId\":\"2\",\"town\":\"湖口县\",\"community\":\"xxx街道社区1\"}],\"activityName\":\"湖口一小护校队申请报名活动名称显示区域活动名称显示区域\",\"type\":\"防汛\",\"personCount\":100,\"file\":\"333.jpg\",\"stateName\":\"\",\"fileUrl\":\"/dev-imgApi/base/skipToken/image/imagePreview/?originalFilename=333.jpg\",\"beginTime\":\"2020-11-29 06:00\",\"endTime\":\"2010-11-30 20:00\",\"id\":\"2\"}";
        JSONObject.parseObject(json1, ActivityManageUpdateReq.class);
    }


}
