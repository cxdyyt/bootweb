package my.springboot.start.bootstudy.service;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ManualKafkaConsumerService {

    private final ConsumerFactory<String, String> consumerFactory;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Consumer<String, String> consumer;
    private boolean running = false;
    private String activeTopic;

    @Autowired
    public ManualKafkaConsumerService(ConsumerFactory<String, String> consumerFactory) {
        this.consumerFactory = consumerFactory;
    }

    // 启动消费者
    public void startConsumer(String topic) {
        if (running) {
            if (topic.equals(activeTopic)) {
                return; // 已经在消费该主题
            }
            stopConsumer(); // 停止当前消费者
        }

        this.activeTopic = topic;
        running = true;
        executorService.submit(() -> {
            // 创建消费者实例
            consumer = consumerFactory.createConsumer();
            consumer.subscribe(Collections.singletonList(topic));

            try {
                while (running) {
                    // 手动轮询消息
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                    // 处理消息
                    for (ConsumerRecord<String, String> record : records) {
                        try {
                            System.out.println("消费消息: " +
                                    "topic=" + record.topic() +
                                    ", partition=" + record.partition() +
                                    ", offset=" + record.offset() +
                                    ", key=" + record.key() +
                                    ", value=" + record.value());

                            // 处理业务逻辑
                            processMessage(record.value());

                        } catch (Exception e) {
                            System.err.println("处理消息失败: " + e.getMessage());
                            // 可以实现重试或错误处理逻辑
                        }
                    }

                    // 手动提交偏移量
                    if (!records.isEmpty()) {
                        consumer.commitSync();
                    }
                }
            } catch (WakeupException e) {
                // 正常关闭
            } catch (Exception e) {
                System.err.println("消费者异常: " + e.getMessage());
            } finally {
                // 关闭消费者
                if (consumer != null) {
                    consumer.close();
                }
            }
        });
    }

    // 停止消费者
    public void stopConsumer() {
        running = false;
        if (consumer != null) {
            consumer.wakeup(); // 中断正在进行的 poll()
        }
    }

    // 处理消息的业务逻辑
    private void processMessage(String message) {
        // 模拟业务处理
        System.out.println("处理业务逻辑: " + message);
    }

    // 手动分配分区并从特定偏移量开始消费
    public void startConsumerFromOffset(String topic, int partition, long offset) {
        if (running) {
            stopConsumer();
        }

        running = true;
        this.activeTopic = topic;

        executorService.submit(() -> {
            consumer = consumerFactory.createConsumer();

            // 手动分配分区
            TopicPartition topicPartition = new TopicPartition(topic, partition);
            consumer.assign(Collections.singletonList(topicPartition));

            // 从指定偏移量开始消费
            consumer.seek(topicPartition, offset);

            try {
                while (running) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("从指定偏移量消费消息: " + record.value());
                        processMessage(record.value());
                    }

                    if (!records.isEmpty()) {
                        consumer.commitSync();
                    }
                }
            } catch (Exception e) {
                System.err.println("消费者异常: " + e.getMessage());
            } finally {
                if (consumer != null) {
                    consumer.close();
                }
            }
        });
    }
}