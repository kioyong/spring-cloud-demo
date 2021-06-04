package com.arch.kafka.stream;

import java.time.Instant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

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

    @Test
    void test2() throws JsonProcessingException {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
                TestChannelBinderConfiguration.getCompleteConfiguration(
                        ChannelBinderConfiguration.class))
                .run("--spring.cloud.function.definition=touppercase;greeting",
                        "--spring.cloud.stream.bindings.greeting-in-0.destination=topic2",
                        "--spring.cloud.stream.bindings.touppercase-in-0.destination=topic1",
                        "--spring.cloud.stream.bindings.touppercase-out-0.destination=topic2")) {
            InputDestination inputDestination = context.getBean(InputDestination.class);
            OutputDestination outputDestination = context.getBean(OutputDestination.class);

            Message<Greeting> inputMessage = MessageBuilder
                    .withPayload(new Greeting("myTest", Instant.now().toString())).build();
            inputDestination.send(inputMessage, "greeting-in-0");

            Message<byte[]> outputMessage = outputDestination.receive(0, "greeting-out-0");
            if (outputMessage != null) {
                String payload = new String(outputMessage.getPayload());
                Greeting greeting = objectMapper.readValue(payload, Greeting.class);
                System.out.println(greeting);
                assertNotNull(greeting);
                assertEquals("myTest".toUpperCase(), greeting.getName());
            }
        }
    }

}
