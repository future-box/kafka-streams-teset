package com.yun27jin.example;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;

public class JsonDeserializer<T extends Serializable> implements Deserializer<T> {

    @Override
    public T deserialize(String topic, byte[] data) {
        return SerializationUtils.deserialize(data);
    }
}
