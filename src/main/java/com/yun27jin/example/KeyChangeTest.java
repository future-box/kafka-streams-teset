package com.yun27jin.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serdes.WrapperSerde;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

public class KeyChangeTest {

    public static void main(String[] args) {
        Serde<String> serde = Serdes.String();
        Serde<Long> serde1 = Serdes.Long();

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "yun27jin");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        StreamsBuilder builder = new StreamsBuilder();
        builder.stream("source" , Consumed.with(serde1, serde))
            .map((key, value) -> KeyValue.pair(value, key))
            .peek((key, value) -> System.out.println("key: " + key + ", value: " + value))
            .to("output", Produced.with(serde, serde1));
        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        streams.start();
    }
}
