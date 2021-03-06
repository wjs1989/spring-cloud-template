package com.wjs.kafka.temporary;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SaslConfigs;

import java.util.Properties;
import java.util.Random;


public class Producer {
    //kafka生产者对象
    private static KafkaProducer<String,String> producer = null;

    public static void main(String[] args) {
        /*发送配置的实例   数据库 JDBC 连接*/
        Properties properties = new Properties();
        /*broker的地址清单*/
        properties.put("bootstrap.servers","10.204.125.254:9092,10.204.125.254:9093,10.204.125.254:9094");
        /*key的序列化器*/
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /*value的序列化器*/
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        properties.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        properties.put(SaslConfigs.SASL_JAAS_CONFIG,
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"isky\" password=\"isky_abc_123_isky\";");


        /*消息生产者*/
        producer = new KafkaProducer<String, String>(properties);

        //商城（各色商品）
        String[] goods = {"iphone","huawei","mi","oppo","vivo"};
        Random r = new Random();//随机数
        Random r1 = new Random();//随机数
        try {//业务
            long startTime = System.currentTimeMillis();//开始时间
            /*待发送的消息实例*/
            ProducerRecord<String,String> record;
            //生成高并发场景下的请求(循环一万次)
            for(int i=1;i<5;i++){
                int goodscount = r.nextInt(10);//随机生成一次购买商品的数量
                StringBuilder sb = new StringBuilder("");//商品列表
                if (goodscount ==0) continue; //避免生成value是空的
                for(int j=0;j<goodscount;j++){
                    //根据商品的数量，生成随机的商品信息，每件商品使用 空格分隔，例如：3个iphone huawei mi
                    sb.append(goods[r1.nextInt(goods.length)]).append(" ");
                }
                try {
                    record = new ProducerRecord<String,String>("topictest1",null,sb.toString());
                    producer.send(record);        /*发送消息--发送后不管*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();//结束时间
            float seconds = (end - startTime) / 1000F;
            System.out.println("生产者数据消耗时间:"+Float.toString(seconds) + " seconds.");
        } finally {
            producer.close();
        }
    }




}
