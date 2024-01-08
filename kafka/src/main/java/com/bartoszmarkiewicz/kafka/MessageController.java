package com.bartoszmarkiewicz.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private KafkaTemplate<String, Message> kafkaTemplate; // private KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, Message> kafkaTemplate) { // public MessageController(KafkaTemplate<String, String> kafkaTemplate)
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
    @PostMapping
    public void publish(@RequestBody MessageRequest request) {
        kafkaTemplate.send("bartosz", request.message());
    }
     */

    @PostMapping
    public void publish(@RequestBody MessageRequest request) {
        Message message = new Message(request.message(), LocalDateTime.now());
        kafkaTemplate.send("bartosz", message); // kafkaTemplate.send("bartosz", request.message());
    }
}