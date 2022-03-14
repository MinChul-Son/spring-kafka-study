package com.minchul.springkafkastudy.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Service;

@Service
public class HelloConsumer2 {
    /**
     * concurrency -> 쓰레드 개수 설정하는 옵션
     * clientIdPrefix -> 콘솔창에 출력될 clientId의 prefix를 설정하는 옵션
     */
    @KafkaListener(id = "test4-listener", topics = "test4-listener", concurrency = "2", clientIdPrefix = "listener_id")
    public void listen(String message, ConsumerRecordMetadata metadata) {
        System.out.println("Listener message= " + message);
        System.out.println("metadata.offset= " + metadata.offset());
        System.out.println("metadata.topic= " + metadata.topic());
        System.out.println("metadata.timestamp= " + metadata.timestamp());
    }

}
