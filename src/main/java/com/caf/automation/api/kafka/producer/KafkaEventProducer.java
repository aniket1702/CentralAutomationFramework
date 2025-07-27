package com.caf.automation.api.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaEventProducer
{
    private KafkaProducer<String,String> producer;



    public KafkaEventProducer()
    {
        Properties props = new Properties();
        String bootstrapServer = "localhost:9092";
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(props);
    }

    public void sendMessage(String topic, String key, String message)
    {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
        producer.send(record);

    }

    public void close() {
        producer.close();
    }
}
