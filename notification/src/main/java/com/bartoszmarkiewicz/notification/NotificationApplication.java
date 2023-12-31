package com.bartoszmarkiewicz.notification;

import com.bartoszmarkiewicz.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(
        scanBasePackages = {
                "com.bartoszmarkiewicz.notification",
                "com.bartoszmarkiewicz.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }


    // TEST RabbitMQ
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
//                                        NotificationConfig notificationConfig) {
//        return args -> {
//            producer.publish(
//                    new Person("Bartosz", 28), //"foo",
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
//
//    record Person(String name, int age) {}

}
