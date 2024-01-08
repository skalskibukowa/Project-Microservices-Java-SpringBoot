package com.bartoszmarkiewicz.order;

import com.bartoszmarkiewicz.amqp.RabbitMQMessageProducer;
import com.bartoszmarkiewicz.clients.fraud.FraudCheckResponse;
import com.bartoszmarkiewicz.clients.fraud.FraudClient;
import com.bartoszmarkiewicz.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final FraudClient fraudClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerOrder(OrderRegistrationRequest request) {

        // Create order
        Order order = Order.builder()
                .orderId(request.orderId())
                .customerId(request.customerId())
                .productId(request.productId())
                .productName(request.productName())
                .orderAmount(request.orderAmount())
                .orderValue(request.orderValue())
                .phoneNumber(request.phoneNumber())
                .shippingAddress(request.shippingAddress())
                .orderStatus(request.orderStatus())
                .orderCreatedAt(LocalDateTime.now())
                .build();

        // Register order in the DB
        orderRepository.saveAndFlush(order);
        // check if fraudster

        /*
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{orderId}", //  Declared microservice FRAUD
                    FraudCheckResponse.class,
                    order.getOrderId()
                );
         */
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudulentOrder(order.getOrderId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                order.getCustomerId(),
                order.getOrderId(),
                order.getProductId(),
                String.format("Order with id %s has been processed", order.getOrderId())
        );


        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );


    }
}
