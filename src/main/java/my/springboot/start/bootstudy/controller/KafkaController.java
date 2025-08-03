package my.springboot.start.bootstudy.controller;

import my.springboot.start.bootstudy.service.KafkaProducerService;
import my.springboot.start.bootstudy.service.ManualKafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService producer;
    @Autowired
    ManualKafkaConsumerService anualKafkaConsumerService;

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        producer.sendMessage("test-topic", message);
        return "消息已发送: " + message;
    }

    @GetMapping("/getManual/{topic}")
    public String getMessageManual(@PathVariable String topic) {
        anualKafkaConsumerService.startConsumer(topic);
        String res = String.format("Begin poll topic %s ",topic);
        return res;
    }
}
