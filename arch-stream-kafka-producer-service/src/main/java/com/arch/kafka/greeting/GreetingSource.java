package com.arch.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaSource {
    String GREETING_GREETING_OUTPUT = "greeting";

    @Output(GREETING_GREETING_OUTPUT)
    SubscribableChannel greetingOutput();
}
