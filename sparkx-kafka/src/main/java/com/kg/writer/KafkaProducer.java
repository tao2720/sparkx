package com.kg.writer;


import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;
//生产者
public class KafkaProducer {
    public static void main(String[] args) throws IOException {
        //0. 申明连接到kafka的配置的url
        Properties props = new Properties();
//        props.put("bootstrap.servers", "192.168.10.106:9092");
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.load(KafkaProducer.class.getClassLoader().getResourceAsStream("producer.properties"));

        //1. 创建生产者对象
//        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        org.apache.kafka.clients.producer.KafkaProducer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);

        //2. 创建你想要发送的记录对象
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("hadoop", null,"hi");
        producer.send(record);

        //3. 释放
        producer.close();
    }
}

