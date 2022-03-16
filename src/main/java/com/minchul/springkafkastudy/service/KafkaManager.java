package com.minchul.springkafkastudy.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AlterConfigOp;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

@Service
public class KafkaManager {

    private final KafkaAdmin kafkaAdmin;
    private final AdminClient adminClient;

    public KafkaManager(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
        this.adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    // 해당 토픽에 관한 설정 정보
    public void describeTopicConfigs() throws ExecutionException, InterruptedException {
        Collection<ConfigResource> resources = List.of(
            new ConfigResource(ConfigResource.Type.TOPIC, "test4-listener")
        );
        DescribeConfigsResult result = adminClient.describeConfigs(resources);
        System.out.println(result.all().get());
    }


    // 해당 토픽에 관한 설정 정보 변경(OpType을 통해 어떤 변경을 할 지 설정)
    public void changeConfig() {
        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, "test4-listener");
        Map<ConfigResource, Collection<AlterConfigOp>> ops = new HashMap<>();
        ops.put(resource, List.of(new AlterConfigOp(new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, "6000"), AlterConfigOp.OpType.SET)));
        adminClient.incrementalAlterConfigs(ops);
    }
}
