package com.bartoszmarkiewicz.order.api.service;

import com.bartoszmarkiewicz.order.dto.OrderRegistrationRequest;
import com.bartoszmarkiewicz.order.model.Order;
import com.bartoszmarkiewicz.order.model.OrderStatus;
import com.bartoszmarkiewicz.order.repository.OrderRepository;
import com.bartoszmarkiewicz.order.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void Order_CreateOrder_ReturnOrder() {

        Order order = Order.builder()
                .customerId(1)
                .productId(1)
                .productName("Orange")
                .orderAmount(20)
                .orderValue(200.00)
                .phoneNumber("555-222-444")
                .shippingAddress("London")
                .orderStatus(OrderStatus.PROGRESS)
                .orderCreatedAt(LocalDateTime.now())
                .build();

        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        OrderRegistrationRequest orderRegistrationRequest =
                OrderRegistrationRequest.builder()
                .customerId(1)
                .productId(1)
                .productName("Orange")
                .orderValue(200.00)
                .orderAmount(20)
                .orderCreatedAt(LocalDateTime.now())
                .shippingAddress("London")
                .phoneNumber("555-222-444")
                .orderStatus(OrderStatus.PROGRESS)
                .build();


        Order savedOrder = orderService.registerOrder(orderRegistrationRequest);

        Assertions.assertThat(savedOrder).isNotNull();

    }
}