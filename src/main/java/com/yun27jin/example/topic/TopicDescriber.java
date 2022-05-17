package com.yun27jin.example.topic;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.consumer.ConsumerConfig;

public class TopicDescriber {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        final String topic = "brand";
        AdminClient adminClient = AdminClient.create(properties);
        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Collections.singleton(topic));

    }
}
