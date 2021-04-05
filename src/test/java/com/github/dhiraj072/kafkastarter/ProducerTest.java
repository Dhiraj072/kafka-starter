package com.github.dhiraj072.kafkastarter;

import static com.github.dhiraj072.kafkastarter.Consumer.A_VALID_TOPIC;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import javax.annotation.Resource;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Trigger producer workflows
 * This test requires:
 * - A Kafka server running at {@link Producer#KAFKA_SERVER_URL}
 *   kafka-server-start.sh config/server.properties
 * - A zookeeper running for the above kafka server
 *   zookeeper-server-start.sh config/zookeeper.properties
 * - A Kafka consumer running
 *   kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic first_topic --group group-first
 */
@SpringBootTest
public class ProducerTest {

    private static final String MSG_KEY = "key_1";

    @Resource
    private Producer producer;

    /**
     * Flush delivered records/refresh producer before each run so that we can assert deterministically
     */
    @BeforeEach
    public void reset() {

        producer.init();
    }

    @Test
    public void sendATestMessage() {

        producer.sendMessage(A_VALID_TOPIC, "test message");
        producer.close();
        assertEquals(1, producer.getDeliveredRecords().size());
    }

    @Test
    public void sendATestMessageWithKey() {

        producer.sendMessage(MSG_KEY, A_VALID_TOPIC, "test message 1");
        producer.sendMessage(MSG_KEY, A_VALID_TOPIC, "test message 2");
        producer.close();
        List<RecordMetadata> delivered = producer.getDeliveredRecords();
        assertEquals(2, delivered.size());
        assertEquals(delivered.get(0).partition(), delivered.get(1).partition());
    }

}
