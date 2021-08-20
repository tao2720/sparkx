package com.kg.reader;

/**
 *
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Date: 2021/08/20
 * @author
 */
//消费者
public class KafkaConsumer {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        //
        props.load(KafkaConsumer.class.getClassLoader().getResourceAsStream("consumer.properties"));

//        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("hadoop"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s ,partition = %d%n", record.offset(), record.key(), record.value(), record.partition());
        }
    }
}
