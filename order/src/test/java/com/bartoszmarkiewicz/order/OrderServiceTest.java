package com.bartoszmarkiewicz.order;


import com.bartoszmarkiewicz.amqp.RabbitMQMessageProducer;
import com.bartoszmarkiewicz.clients.fraud.FraudCheckResponse;
import com.bartoszmarkiewicz.clients.fraud.FraudClient;
import com.bartoszmarkiewicz.clients.notification.NotificationRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private FraudClient fraudClient;

    @Mock
    private RabbitMQMessageProducer rabbitMQMessageProducer;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setOrderId(1);
        order.setCustomerId(1);
        order.setProductId(1);
        order.setProductName("Apple");
        order.setOrderValue(100.00);
        order.setOrderAmount(100);
        order.setShippingAddress("Krakow");
        order.setPhoneNumber("2023034");
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderCreatedAt(LocalDateTime.now());
    }


    @Test
    public void registerOrderShouldSaveOrderAndSendNotification() throws Exception {
        // Arrange
        given(orderRepository.saveAndFlush(order)).willReturn(order);
        given(fraudClient.isFraudulentOrder(order.getOrderId())).willReturn(new FraudCheckResponse(false));

        // Act
        orderService.registerOrder(new OrderRegistrationRequest(
                order.getOrderId(),
                order.getCustomerId(),
                order.getProductId(),
                order.getProductName() ,
                order.getOrderValue(),
                order.getOrderAmount(),
                order.getOrderCreatedAt(),
                order.getShippingAddress(),
                order.getPhoneNumber(),
                OrderStatus.PROGRESS));

        // Verify
        verify(orderRepository).saveAndFlush(order);
        verify(fraudClient).isFraudulentOrder(order.getOrderId());
        verify(rabbitMQMessageProducer).publish(
                any(NotificationRequest.class),
                anyString(),
                anyString()
        );
    }
}