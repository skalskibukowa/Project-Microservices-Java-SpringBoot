package com.bartoszmarkiewicz.order;

import com.bartoszmarkiewicz.amqp.RabbitMQMessageProducer;
import com.bartoszmarkiewicz.clients.fraud.FraudCheckResponse;
import com.bartoszmarkiewicz.clients.fraud.FraudClient;
import com.bartoszmarkiewicz.clients.inventory.ProductClient;
import com.bartoszmarkiewicz.clients.inventory.ProductResponse;
import com.bartoszmarkiewicz.clients.notification.NotificationRequest;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final FraudClient fraudClient;

    private final ProductClient productClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public Order registerOrder(OrderRegistrationRequest request) {

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
                .orderStatus(OrderStatus.PROGRESS)
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

        ProductResponse productResponse = productClient.getProductById(order.getProductId()).getBody().get();

        int updatedQuantity = productResponse.productQuantity() - order.getOrderAmount();

        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("productQuantity", updatedQuantity);

        productClient.updateProductQuantity(order.getProductId(), requestBody);

        System.out.println(updatedQuantity);

        System.out.println(requestBody);


        //int updatedQuantity = productResponse1.getBody().get().productQuantity()  - productResponse.productQuantity();

        System.out.println(productResponse);

        if(productResponse.productQuantity() < order.getOrderAmount() ) {
            throw new IllegalStateException("No product");
        }

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


        return order;
    }
}
