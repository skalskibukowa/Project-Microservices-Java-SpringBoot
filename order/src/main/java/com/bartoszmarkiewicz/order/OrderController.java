package com.bartoszmarkiewicz.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void registerOrder(@RequestBody OrderRegistrationRequest orderRegistrationRequest) {
        log.info("new order registration {}", orderRegistrationRequest);
        orderService.registerOrder(orderRegistrationRequest);
    }
}
