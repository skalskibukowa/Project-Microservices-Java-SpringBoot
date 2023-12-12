package com.bartoszmarkiewicz.order;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterOrder() {
        orderService.registerOrder(new OrderRegistrationRequest(1L, "Product Name", 10.0d, 1,
                LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144", OrderStatus.PROGRESS));
    }
}
