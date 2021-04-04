package com.github.dhiraj072.kafkastarter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

/**
 * A wrapper class to instantiate and use {@link KafkaProducer}.
 * Sample usage in corresponding test.
 */
@Service
public class Producer {

    // URL for the running kafka bootstrap server
    public static final String KAFKA_SERVER_URL = "127.0.0.1:9092";

    private List<RecordMetadata> deliveredRecords;
    private KafkaProducer<String, String> producer;

    public Producer() {

    }

    /**
     * Create the kafka producer with required props
     */
    @PostConstruct
    public void init() {

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
        deliveredRecords = new ArrayList<>();
    }

    public void sendMessage(String topic, String msg) {

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
        producer.send(record, (metadata, exception) -> {

            if (exception == null)
                deliveredRecords.add(metadata);
        });
        producer.close();
    }

    public List<RecordMetadata> getDeliveredRecords() {

        return deliveredRecords;
    }
}
