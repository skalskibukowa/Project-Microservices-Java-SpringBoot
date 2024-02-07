package com.bartoszmarkiewicz.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import com.bartoszmarkiewicz.order.controller.OrderController;
import com.bartoszmarkiewicz.order.dto.OrderRegistrationRequest;
import com.bartoszmarkiewicz.order.model.Order;
import com.bartoszmarkiewicz.order.model.OrderStatus;
import com.bartoszmarkiewicz.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class OrderControllerDiffblueTest {
    /**
     * Method under test:
     * {@link OrderController#registerOrder(OrderRegistrationRequest)}
     */
    @Test
    void testRegisterOrder() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.bartoszmarkiewicz.order.dto.OrderRegistrationRequest["orderCreatedAt"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1276)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:770)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4487)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3742)
        //   See https://diff.blue/R013 to resolve this issue.

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
        OrderService orderService = mock(OrderService.class);
        when(orderService.registerOrder(Mockito.<OrderRegistrationRequest>any())).thenReturn(order);
        OrderController orderController = new OrderController(orderService);

        // Act
        ResponseEntity<Order> actualRegisterOrderResult = orderController
                .registerOrder(new OrderRegistrationRequest(1, 1, 1, "Product Name", 10.0d, 1,
                        LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144", OrderStatus.PROGRESS));

        // Assert
        verify(orderService).registerOrder(Mockito.<OrderRegistrationRequest>any());
        assertEquals(HttpStatus.CREATED, actualRegisterOrderResult.getStatusCode());
        assertTrue(actualRegisterOrderResult.hasBody());
        assertTrue(actualRegisterOrderResult.getHeaders().isEmpty());
    }
}
