package com.bartoszmarkiewicz.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "order",
            groupId = "POS-1" //,
            //containerFactory = "messageFactory" check why is added
    )
    public void listener(Message data) { // void listener(String data) {
        System.out.println("Listener received " + data.message().toLowerCase() + " :D"); // System.out.println("Listener received " + data + " :D");
    }
}
