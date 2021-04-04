package com.github.dhiraj072.kafkastarter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;
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

    // A valid topic that should exist on your kafka server
    private static final String A_VALID_TOPIC = "first_topic";

    @Resource
    private Producer producer;

    @Test
    public void sendATestMessage() {

        assertEquals(0, producer.getDeliveredRecords().size());
        producer.sendMessage(A_VALID_TOPIC, "test message");
        assertEquals(1, producer.getDeliveredRecords().size());
    }

}
