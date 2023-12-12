package com.bartoszmarkiewicz.order;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class OrderControllerTest {
    @Test
    void testRegisterOrder() {


        OrderService orderService = mock(OrderService.class);
        doNothing().when(orderService).registerOrder(Mockito.<OrderRegistrationRequest>any());
        OrderController orderController = new OrderController(orderService);
        orderController.registerOrder(new OrderRegistrationRequest(1L, "Product Name", 10.0d, 1,
                LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144", OrderStatus.PROGRESS));
        verify(orderService).registerOrder(Mockito.<OrderRegistrationRequest>any());
    }
}
