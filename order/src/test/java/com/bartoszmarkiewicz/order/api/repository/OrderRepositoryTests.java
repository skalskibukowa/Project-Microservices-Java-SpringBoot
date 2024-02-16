package com.bartoszmarkiewicz.order.api.repository;


import com.bartoszmarkiewicz.order.model.Order;
import com.bartoszmarkiewicz.order.repository.OrderRepository;
import com.bartoszmarkiewicz.order.model.OrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void OrderRepository_Save_ReturnSavedOrder() {
        // Arrange
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

        // Act
        Order savedOrder = orderRepository.save(order);

        // Assert
        Assertions.assertThat(savedOrder).isNotNull();
        Assertions.assertThat(savedOrder.getOrderId()).isGreaterThan(0);
        Assertions.assertThat(savedOrder.getOrderAmount()).isEqualTo(20);

    }

}