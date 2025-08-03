package my.springboot.start.bootstudy.service;

import my.springboot.start.bootstudy.entity.MessageItem;
import my.springboot.start.bootstudy.mapper.MessageItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KafkaConsumerService {

    @Autowired
    private MessageItemMapper messageItemMapper;

    // Exactly-once-like consumer: manual ack and idempotent DB write
    @KafkaListener(topics = "eos-demo", groupId = "eos-group")
    @Transactional
    public void consume(@Payload String payload,
                        @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String messageId,
                        Acknowledgment ack) {
        try {
            String effectiveId = (messageId != null && !messageId.isEmpty()) ? messageId : String.valueOf(payload.hashCode());
            // Idempotent insert (ON DUPLICATE KEY does nothing)
            MessageItem item = new MessageItem();
            item.setMessageId(effectiveId);
            item.setPayload(payload);
            messageItemMapper.insertIgnore(item);

            ack.acknowledge();
        } catch (Exception e) {
            // leave unacked; it will be redelivered
            throw e;
        }
    }
}
