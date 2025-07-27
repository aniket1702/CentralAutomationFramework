package com.caf.automation.api.kafka.consumer;

import com.caf.automation.config.ConfigFactory;
import com.caf.automation.loggers.LogType;
import com.caf.automation.loggers.LogUtils;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class KafkaEventConsumer {

    private final KafkaConsumer<String, String> consumer;
    @Getter
    private List<String> consumedMessages = new ArrayList<>();

    public KafkaEventConsumer(String groupId) {
        String bootstrapServer = "localhost:9092";
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        this.consumer = new KafkaConsumer<>(props);
    }

    public void consumeMessages(String topic)
    {
        consumer.subscribe(Collections.singletonList(topic));
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        records.forEach(consumerRecord -> {
            consumedMessages.add(consumerRecord.value());
            LogUtils.log(LogType.PASS,"Messages consumed from Kafka topic: "+ ConfigFactory.getConfig().kafkaTopic()+"\n"+consumedMessages);
        });

    }

    public void close() {
        consumer.close();
    }
}
