package com.bartoszmarkiewicz.clients.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "order")
public interface OrderClient {

    @GetMapping(path = "api/v1/{productId}")
    OrderResponse orderRequest(@PathVariable("productId") Integer productId);
}
