package com.arch.kafka.stream;

import java.time.Instant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(classes = ArchKafkaStreamApplication.class)
@Import({TestChannelBinderConfiguration.class})
class ArchKafkaStreamApplicationTests {

    @Autowired
    private InputDestination input;

    @Autowired
    private OutputDestination output;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        input.send(new GenericMessage<>(new Greeting("hello", Instant.now().toString())), "topic1");
        Message<byte[]> receive2 = output.receive(1, "topic2");
        if (receive2 != null) {
            String payload = new String(receive2.getPayload());
            Greeting greeting = objectMapper.readValue(payload, Greeting.class);
            System.out.println(greeting);
            assertNotNull(greeting);
            assertEquals("hello".toUpperCase(), greeting.getName());
        }
        else {
            fail("can receive message from topic2");
        }
    }
}
