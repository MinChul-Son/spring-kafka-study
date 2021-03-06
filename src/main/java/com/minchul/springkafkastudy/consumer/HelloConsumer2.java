package com.minchul.springkafkastudy.consumer;

import javax.validation.Valid;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.minchul.springkafkastudy.model.Animal;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HelloConsumer2 {
    /**
     * concurrency -> 쓰레드 개수 설정하는 옵션
     * clientIdPrefix -> 콘솔창에 출력될 clientId의 prefix를 설정하는 옵션
     */
    @KafkaListener(id = "test4-listener", topics = "test4-listener", concurrency = "2", clientIdPrefix = "listener_id")
    public void listen(String message, ConsumerRecordMetadata metadata,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
                       @Header(KafkaHeaders.OFFSET) long offset,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("Listener message= " + message);
        System.out.println("metadata.offset= " + metadata.offset());
        System.out.println("metadata.topic= " + metadata.topic());
        System.out.println("metadata.timestamp= " + metadata.timestamp());

        System.out.println("header.offset= " + offset);
        System.out.println("header.topic= " + topic);
        System.out.println("header.timestamp= " + timestamp);
    }

    @KafkaListener(id = "test4-animal-listener", topics = "test4-animal", containerFactory = "kafkaJsonContainerFactory", errorHandler = "validationHandler")
    public void listenAnimal(@Valid Animal animal) {
        log.info("animal={}", animal);
    }

}
