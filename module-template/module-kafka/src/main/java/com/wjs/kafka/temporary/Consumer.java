package com.wjs.kafka.temporary;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    //kafka消费者对象
    private static KafkaConsumer<String,String> consumer = null;

    public static void main(String[] args) {
        /*发送配置的实例*/
        Properties properties = new Properties();
        /*broker的地址清单*/
        properties.put("bootstrap.servers","118.24.22.139:9092");
        /*key的反序列化器*/
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        /*value的反序列化器*/
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        /*每次获取数据从头开始*/
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        /*消费者的群组id*/
        properties.put("group.id","wenjs");
        /*消息者*/
        consumer = new KafkaConsumer<String, String>(properties);
        try {
            long startTime = System.currentTimeMillis();
            //订阅 主题 mall
            consumer.subscribe(Collections.singletonList("topictest"));
            while(true){  //无限循环,
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                if(records.count() ==0){
                   continue;
                }
                for(ConsumerRecord<String, String> record:records){
                    System.out.println(String.format(
                            ":主题：%s，序号(偏移量)：%d，信息：%s",
                            record.topic(),record.offset(),record.value()));
                    Thread.sleep(10); //1秒钟处理100条(一订单包含多个商品)
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            consumer.close();
        }
    }




}
