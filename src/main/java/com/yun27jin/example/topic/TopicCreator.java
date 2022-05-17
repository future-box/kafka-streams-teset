package com.yun27jin.example.topic;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;

public class TopicCreator {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        AdminClient adminClient = AdminClient.create(properties);
        final String topic = "brand";
        int partitions = 1;
        short replicationFactor = 1;
        NewTopic newTopic = new NewTopic(topic, partitions, replicationFactor);
        CreateTopicsResult topics = adminClient.createTopics(Collections.singletonList(newTopic));
        System.out.println(topics);
        adminClient.close();
    }
}
