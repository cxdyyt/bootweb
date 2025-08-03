package my.springboot.start.bootstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// 生产者服务
@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.executeInTransaction(kt -> {
            kt.send(topic, message);
            return null;
        });
    }

    public void sendMessageWithKey(String topic, String key, String message) {
        kafkaTemplate.executeInTransaction(kt -> {
            kt.send(topic, key, message);
            return null;
        });
    }
}


