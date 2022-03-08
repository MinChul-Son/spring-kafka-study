package com.minchul.springkafkastudy.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class HelloConsumer {

    @KafkaListener(id = "kafka3-id", topics = "topic3")
    public void listen(String message) {
        System.out.println(message);
    }

    @KafkaListener(id = "kafka3-bytes-id", topics = "topic3-bytes")
    public void listenBytes(String message) {
        System.out.println(message);
    }

    @KafkaListener(id = "kafka3-request-id", topics = "topic3-request")
    @SendTo
    public String listenRequest(String message) {
        System.out.println(message);
        return "Pong Topic3";
    }
}
