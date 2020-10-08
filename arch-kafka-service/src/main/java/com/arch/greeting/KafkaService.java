package com.arch.greeting;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class KafkaService {

    private final KafkaTemplate kafkaTemplate;


    @StreamListener(Sink.INPUT)
    public void process(Message<?> message) {
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            System.out.println("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
    }
//}

//    @KafkaListener(topics = "greeting1")
//    public void processMessage(ConsumerRecord<?, ?> cr) {
//        GreetingResponse gr = (GreetingResponse) cr.value();
//        log.info("receive message: " + gr.toString());
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void trySendMessage() {
        log.info("try to send greeting message");
//        for (int i = 0; i < 10; i++) {
//            kafkaTemplate.send("greeting1", String.valueOf(i), new GreetingResponse("hello " + " @ " + Instant.now().toString(), "liangyong"));
//        }
        log.info("send message done");
    }

}
