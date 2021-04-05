package com.github.dhiraj072.kafkastarter;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConsumerTest {

    @Resource
    private Producer producer;

    @Resource
    private Consumer consumer;

    @Test
    public void testConsumerReceivesTheMessage() {

        producer.sendMessage(Consumer.A_VALID_TOPIC, "message from consumer test");
        producer.close();
        ConsumerRecords<String, String> received = consumer.poll();
        assertFalse( received.isEmpty(), "Should receive some messages");
    }
}