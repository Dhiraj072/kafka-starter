package com.github.dhiraj072.kafkastarter;

import static com.github.dhiraj072.kafkastarter.Producer.KAFKA_SERVER_URL;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    // A valid topic that should exist on your kafka server
    public static final String A_VALID_TOPIC = "first_topic";

    private KafkaConsumer<String, String> consumer;

    /**
     * Create the kafka producer with required props
     */
    @PostConstruct
    public void init() {

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group-java-consumer");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton(A_VALID_TOPIC));
    }

    public ConsumerRecords<String, String> poll() {

        return consumer.poll(Duration.ofMillis(1000));
    }
}
