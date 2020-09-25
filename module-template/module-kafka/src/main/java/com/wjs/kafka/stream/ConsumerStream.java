package com.wjs.kafka.stream;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Printed;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

public class ConsumerStream {
    static final Pattern pattern = Pattern.compile("\\W+");

    public static void main(String[] args) throws Exception{

        Properties props = new Properties();
        /*每个stream应用都必须有唯一的id*/
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "phone_count");
        /*kafka的地址*/
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "118.24.22.139:9092");
        /*消息的序列化机制*/
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        /*每次获取数据从头开始*/
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        /*流的建造器*/

        StreamsBuilder builder = new StreamsBuilder();
        /*流的输入源，kafka中主题phone*/
        KStream<String, String> source = builder.stream("topictest");

        final  KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.cleanUp();
        streams.start();

        /*正则负责拆分文本为单词*/
        KStream counts  =
                source.flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
                .map((key, value) -> new KeyValue<Object, Object>(value, value))
                .filter((key, value) -> (value.equals("huawei")))
                .groupByKey()
                .count()
                .mapValues(value->Long.toString(value)).toStream();
        //打印出来
        //counts.to("output");//通过to方法的将结果写入Kafka的主题 output中
        counts.print( Printed.toSysOut());

        //counts.to("topic0");
        //KTable<String, String> table = builder.table("topic0");
    }

}
