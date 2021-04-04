package com.github.dhiraj072.kafkastarter;

import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

/**
 * A wrapper class to instantiate and use {@link KafkaProducer}.
 * For tests and sampke usage in corresponding test.
 */
@Service
public class Producer {

    // URL for the running kafka bootstrap server
    public static final String KAFKA_SERVER_URL = "127.0.0.1:9092";

    private KafkaProducer<String, String> producer;

    public Producer() {

    }

    /**
     * create the kafka producer with required props
     */
    @PostConstruct
    public void init() {

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
    }

    public void sendTestMessage() {

        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "hello world from java");
        producer.send(record);
        producer.close();
    }
}
