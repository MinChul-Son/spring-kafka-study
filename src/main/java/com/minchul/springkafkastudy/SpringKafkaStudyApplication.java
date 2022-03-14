package com.minchul.springkafkastudy;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import com.minchul.springkafkastudy.producer.HelloProducer;
import com.minchul.springkafkastudy.producer.HelloProducer2;

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

    @Bean
    public ApplicationRunner runner(HelloProducer2 producer, KafkaMessageListenerContainer<String, String> container) {
        return args -> {
//            producer.async("test4", "Hello, Container!!");
//            container.start();
//            container.pause();
//            container.resume();
//            container.stop();

            producer.async("test4-listener", "Hello, Kafka Listener");
        };
    }
}
