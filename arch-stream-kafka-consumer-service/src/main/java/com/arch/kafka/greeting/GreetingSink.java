package com.arch.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaSink {
    String GREETING_GREETING_INPUT = "greeting";

    @Input(GREETING_GREETING_INPUT)
    SubscribableChannel greetingInput();
}
