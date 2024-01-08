package com.bartoszmarkiewicz.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
public class KafkaApplication
{
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Message> kafkaTemplate) { // CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate)
        return args -> {
            for (int i = 0; i < 100; i++) {
                kafkaTemplate.send("bartosz", new Message( "Kafka " + i, LocalDateTime.now()) ); // kafkaTemplate.send("bartosz", "hello " + i );
            }
        };
    }
}
