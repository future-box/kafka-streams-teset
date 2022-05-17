package com.yun27jin.example.consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MyConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, Integer> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("brand"));
        ConsumerRecords<String, Integer> records = consumer.poll(Duration.ofMillis(100_000));
        for (ConsumerRecord<String, Integer> record : records) {
            System.out.println(record.key() + ":" + record.value());
        }
    }
}
