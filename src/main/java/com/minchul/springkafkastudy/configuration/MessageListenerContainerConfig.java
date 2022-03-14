package com.minchul.springkafkastudy.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import com.minchul.springkafkastudy.consumer.listener.DefaultMessageListener;

@Configuration
public class MessageListenerContainerConfig {

    @Bean
    public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer() {
        ContainerProperties containerProps = new ContainerProperties("test4");
        containerProps.setGroupId("test4-container");
        containerProps.setAckMode(ContainerProperties.AckMode.BATCH);
        containerProps.setMessageListener(new DefaultMessageListener());
        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(
            containerFactory(),
            containerProps);
        container.setAutoStartup(false);

        return container;
    }

    private ConsumerFactory<String, String> containerFactory() {
        return new DefaultKafkaConsumerFactory<>(props());
    }

    private Map<String, Object> props() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
}
