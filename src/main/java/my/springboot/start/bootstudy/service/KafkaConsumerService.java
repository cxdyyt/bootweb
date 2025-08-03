package my.springboot.start.bootstudy.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerService {

    // 简单消费者
    @KafkaListener(topics = "test-topic", groupId = "simple-group")
    public void listenSimple(String message) {
        System.out.println("简单消费: " + message);
    }

    // 带分区和偏移量信息的消费者
    @KafkaListener(topics = "metadata-topic", groupId = "metadata-group")
    public void listenWithMetadata(@Payload String message,
                                   @Header(KafkaHeaders.PARTITION_ID) int partition,
                                   @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.println("消费消息: " + message + ", 分区: " + partition + ", offset: " + offset);
    }

    // 手动提交偏移量的消费者
    @KafkaListener(topics = "manual-commit-topic", groupId = "manual-commit-group")
    public void listenManualCommit(String message, Acknowledgment ack) {
        try {
            System.out.println("手动提交消费: " + message);
            // 处理完成后手动提交
            ack.acknowledge();
        } catch (Exception e) {
            System.err.println("处理失败: " + e.getMessage());
            // 可以选择不提交或稍后提交
        }
    }

    // 批量消费者
    @KafkaListener(topics = "batch-topic", groupId = "batch-group")
    public void listenBatch(List<String> messages) {
        System.out.println("批量消费: " + messages.size() + " 条消息");
        messages.forEach(msg -> System.out.println("批量消息: " + msg));
    }
}
