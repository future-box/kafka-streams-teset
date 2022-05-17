package com.yun27jin.example.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.Serializer;

import com.yun27jin.example.model.Product;
import com.yun27jin.example.serializer.GsonSerializer;

public class CustomSerializerProducer {

    public static void main(String[] args) {
        LongSerializer longSerializer = new LongSerializer();
        Serializer<Product> productSerializer = new GsonSerializer<>();

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        Producer<Long, Product> producer = new KafkaProducer<>(properties, longSerializer, productSerializer);

        String topic = "brand";
        Product product = new Product();
        product.setProductId(1L);
        product.setBrandNm("나이키");
        product.setDispNm("에어맥스 95");
        ProducerRecord<Long, Product> record = new ProducerRecord<>(topic, product.getProductId(), product);
        try {
            producer.send(record).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
