package com.minchul.springkafkastudy.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HelloConsumer4 {

    @KafkaListener(id = "test6-listener", topics = "test6")
    public void listen(String message) {
        log.info("message={}", message);
    }
}
