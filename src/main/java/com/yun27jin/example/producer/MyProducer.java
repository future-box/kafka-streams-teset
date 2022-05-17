package com.yun27jin.example.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class MyProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        String topic = "brand";
        KafkaProducer<Long, String> productProducer = new KafkaProducer<>(properties);
        ProducerRecord<Long, String> record = new ProducerRecord<>(topic, 1L, "nike");
        productProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                System.out.println(metadata);
            }
        });
    }
}
