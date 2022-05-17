package com.yun27jin.example.topic;

import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.consumer.ConsumerConfig;

public class TopicLister {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        AdminClient adminClient = AdminClient.create(properties);
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        try {
            Map<String, TopicListing> stringTopicListingMap = listTopicsResult.namesToListings().get();
            System.out.println(stringTopicListingMap);
        } catch (Exception ignore) {
        }
    }
}
