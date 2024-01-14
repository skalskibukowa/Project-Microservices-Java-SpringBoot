package com.bartoszmarkiewicz.order;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bartoszmarkiewicz.amqp.RabbitMQMessageProducer;
import com.bartoszmarkiewicz.clients.fraud.FraudCheckResponse;
import com.bartoszmarkiewicz.clients.fraud.FraudClient;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceDiffblueTest {
    @MockBean
    private FraudClient fraudClient;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @MockBean
    private RabbitMQMessageProducer rabbitMQMessageProducer;

    /**
     * Method under test:
     * {@link OrderService#registerOrder(OrderRegistrationRequest)}
     */
    @Test
    void testRegisterOrder() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductId(1);
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        when(orderRepository.saveAndFlush(Mockito.<Order>any())).thenReturn(order);
        when(fraudClient.isFraudulentOrder(Mockito.<Integer>any())).thenReturn(new FraudCheckResponse(true));

        // Act and Assert
        assertThrows(IllegalStateException.class,
                () -> orderService.registerOrder(new OrderRegistrationRequest(1, 1, 1, "Product Name", 10.0d, 1,
                        LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144", OrderStatus.PROGRESS)));
        verify(fraudClient).isFraudulentOrder(Mockito.<Integer>any());
        verify(orderRepository).saveAndFlush(Mockito.<Order>any());
    }

    /**
     * Method under test:
     * {@link OrderService#registerOrder(OrderRegistrationRequest)}
     */
    @Test
    void testRegisterOrder2() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductId(1);
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        when(orderRepository.saveAndFlush(Mockito.<Order>any())).thenReturn(order);
        when(fraudClient.isFraudulentOrder(Mockito.<Integer>any())).thenThrow(new IllegalStateException("foo"));

        // Act and Assert
        assertThrows(IllegalStateException.class,
                () -> orderService.registerOrder(new OrderRegistrationRequest(1, 1, 1, "Product Name", 10.0d, 1,
                        LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144", OrderStatus.PROGRESS)));
        verify(fraudClient).isFraudulentOrder(Mockito.<Integer>any());
        verify(orderRepository).saveAndFlush(Mockito.<Order>any());
    }

    /**
     * Method under test:
     * {@link OrderService#registerOrder(OrderRegistrationRequest)}
     */
    @Test
    void testRegisterOrder3() {
        // Arrange
        Order order = new Order();
        order.setCustomerId(1);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductId(1);
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        when(orderRepository.saveAndFlush(Mockito.<Order>any())).thenReturn(order);
        when(fraudClient.isFraudulentOrder(Mockito.<Integer>any())).thenReturn(new FraudCheckResponse(false));
        doNothing().when(rabbitMQMessageProducer)
                .publish(Mockito.<Object>any(), Mockito.<String>any(), Mockito.<String>any());

        // Act
        orderService.registerOrder(new OrderRegistrationRequest(1, 1, 1, "Product Name", 10.0d, 1,
                LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144", OrderStatus.PROGRESS));

        // Assert
        verify(rabbitMQMessageProducer).publish(Mockito.<Object>any(), Mockito.<String>any(), Mockito.<String>any());
        verify(fraudClient).isFraudulentOrder(Mockito.<Integer>any());
        verify(orderRepository).saveAndFlush(Mockito.<Order>any());
    }
}
