package com.minchul.springkafkastudy.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public class DefaultMessageListener implements MessageListener<String, String> {
    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        System.out.println("Default Listener.Message= " + data.value());
    }
}
