package com.minchul.springkafkastudy;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListenerContainer;

import com.minchul.springkafkastudy.consumer.HelloConsumer3;
import com.minchul.springkafkastudy.model.Animal;
import com.minchul.springkafkastudy.producer.HelloProducer;
import com.minchul.springkafkastudy.producer.HelloProducer2;
import com.minchul.springkafkastudy.service.KafkaManager;

@SpringBootApplication
public class SpringKafkaStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaStudyApplication.class, args);
    }

//    @Bean
//    public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate) {
//        return args -> {
//            kafkaTemplate.send("quickstart-events", "hello-world");
//        };
//    }

//    @Bean
//    public ApplicationRunner runner(AdminClient adminClient) {
//        return args -> {
//            ListTopicsResult listTopicsResult = adminClient.listTopics();
//            KafkaFuture<Map<String, TopicListing>> mapKafkaFuture = listTopicsResult.namesToListings();
//            Map<String, TopicListing> stringTopicListingMap = mapKafkaFuture.get();
//            for (String topicName : stringTopicListingMap.keySet()) {
//                System.out.println("topicName =" + topicName);
//                TopicListing topicListing = stringTopicListingMap.get(topicName);
//                System.out.println(topicListing);
//
//                Map<String, TopicDescription> description = adminClient.describeTopics(Collections.singleton(topicName))
//                                                                                     .all()
//                                                                                     .get();
//                System.out.println(description);
//                adminClient.deleteTopics(Collections.singleton(topicName));
//            }
//        };
//    }

//    @Bean
//    public ApplicationRunner runner(HelloProducer producer) {
//        return args -> {
//            producer.async("topic3", "Hello World!(async)");
//            producer.sync("topic3", "Hello World!(sync)");
//            producer.routingSend("topic3", "Hello World!(routing)");
//            producer.routingSendBytes("topic3-bytes", "Hello World!(routing-bytes)".getBytes(StandardCharsets.UTF_8));
//            producer.replyingSend("topic3-request", "Ping Topic3");
//        };
//    }

//    @Bean
    public ApplicationRunner runner(HelloProducer2 producer, KafkaMessageListenerContainer<String, String> container) {
        return args -> {
//            producer.async("test4", "Hello, Container!!");
//            container.start();
//            container.pause();
//            container.resume();
//            container.stop();

//            producer.async("test4-listener", "Hello, Kafka Listener");
            producer.asyncAnimal("test4-animal", new Animal("멍멍이", 2));
//            producer.asyncAnimal("test4-animal", new Animal("멍멍이", 15)); -> 검증을 통과하지 못해 오류가 발생함

        };
    }

//    @Bean
    public ApplicationRunner runner(KafkaManager kafkaManager, KafkaTemplate<String, String> kafkaTemplate, HelloConsumer3 consumer) {
        return args -> {
            kafkaManager.changeConfig();
            kafkaManager.describeTopicConfigs();
//            kafkaManager.deleteRecords();
            kafkaManager.findAllConsumerGroups();
//            kafkaManager.deleteConsumerGroup();
//            kafkaManager.findAllConsumerGroups();
            kafkaManager.findAllOffsets();

            kafkaTemplate.send("test5-listener", "Hello!! Test5 Listener");
            consumer.seek();
        };
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate, KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry) {
        return args -> {
            Map<MetricName, ? extends Metric> producerMetrics = kafkaTemplate.metrics();

            MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer("test6-listener");
            Map<String, Map<MetricName, ? extends Metric>> consumerMetrics = container.metrics();
        };
    }
}
