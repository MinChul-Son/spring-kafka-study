package com.minchul.springkafkastudy.configuration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {
//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name("from_topic_builder").build();
//    }
//
//    @Bean
//    public KafkaAdmin.NewTopics topics() {
//        return new KafkaAdmin.NewTopics(
//            TopicBuilder.name("from-topic-builder1").build(),
//            TopicBuilder.name("from-topic-builder2")
//                .partitions(3)
//                .replicas(1)
//                .config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(1000 * 60 * 60)) // Retention 시간을 60분으로 설정
//                        .build()
//        );
//    }
//
//    @Bean
//    public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
//        return AdminClient.create(kafkaAdmin.getConfigurationProperties());
//    }

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
            TopicBuilder.name("topic3").build()
        );
    }
}
