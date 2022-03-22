package com.minchul.springkafkastudy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ValidationErrorHandlerConfig {

    @Bean
    public KafkaListenerErrorHandler validationHandler() {
        return new KafkaListenerErrorHandler() {
            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
                log.error("validation error message={}, exception={}", message, exception);
                return this;
            }
        };
    }
}
