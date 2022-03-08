package com.minchul.springkafkastudy;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringKafkaStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaStudyApplication.class, args);
    }

//    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            kafkaTemplate.send("quickstart-events", "hello-world");
        };
    }
}
