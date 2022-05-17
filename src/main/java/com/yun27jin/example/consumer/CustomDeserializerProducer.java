package com.yun27jin.example.consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.LongDeserializer;

import com.yun27jin.example.deserializer.GsonDeserializer;
import com.yun27jin.example.model.Product;

public class CustomDeserializerProducer {

    public static void main(String[] args) {
        Deserializer<Product> productDeserializer = new GsonDeserializer<>(Product.class);
        LongDeserializer longDeserializer = new LongDeserializer();

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Consumer<Long, Product> consumer = new KafkaConsumer<>(properties, longDeserializer, productDeserializer);
        consumer.subscribe(Collections.singletonList("brand"));
        ConsumerRecords<Long, Product> records = consumer.poll(Duration.ofMillis(100000));
        for (ConsumerRecord<Long, Product> record : records) {
            System.out.println(record.key() + " : " + record.value());
        }
    }
}
