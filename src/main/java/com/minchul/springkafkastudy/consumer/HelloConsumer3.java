package com.minchul.springkafkastudy.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HelloConsumer3 extends AbstractConsumerSeekAware {

    @KafkaListener(id = "test5-listener-id", topics = "test5-listener")
    public void listen(String message) {
      log.info("message={}", message);
    }

    // getSeekCallbacks는 현재 파티션에 등록된 모든 콜백을 가져옴
    // getSeekCallbacksFor은 지정한 것 하나 가져옴
    public void seek() {
        getSeekCallbacks().forEach(((topicPartition, consumerSeekCallback) -> consumerSeekCallback.seek(topicPartition.topic(),
                                                                                                        topicPartition.partition(),
                                                                                                        0)));
    }
}
