package com.yun27jin.example;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Serializer;

public class JsonSerializer<T extends Serializable> implements Serializer<T> {

    @Override
    public byte[] serialize(String topic, T data) {
        return SerializationUtils.serialize(data);
    }
}
