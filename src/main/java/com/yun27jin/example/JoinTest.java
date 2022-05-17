package com.yun27jin.example;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Predicate;

public class JoinTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "yun27jin");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        Predicate<String, String> babo = (key, value) -> key.equals("babo");
        KTable<String, String> kTable = builder.<String, String>table("address").filter(babo);
        KStream<String, String> kStream = builder.stream("order");
        kStream.join(kTable, (order, address) -> order + address)
            .to("partner");
        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        streams.start();
    }
}
