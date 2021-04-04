package com.github.dhiraj072.kafkastarter;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Trigger producer workflows
 */
@SpringBootTest
public class ProducerTest {

    @Resource
    private Producer producer;

    @Test
    public void sendATestMessage() {

        producer.sendTestMessage();
    }

}
