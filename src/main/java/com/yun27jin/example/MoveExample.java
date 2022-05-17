package com.yun27jin.example;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

public class MoveExample {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "yun27jin");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        Serde<String> stringSerde = Serdes.String();

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> sourceProcessor = builder.stream("source", Consumed.with(stringSerde, stringSerde));
        KStream<String, String> upperCaseProcessor = sourceProcessor.mapValues(value -> value.toUpperCase());
        upperCaseProcessor.to("output", Produced.with(stringSerde, stringSerde));

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        streams.start();
    }
}
