package com.bartoszmarkiewicz.clients.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("inventory")
public interface ProductClient {
    @GetMapping(path = "api/v1/inventories/{orderId}")
    ProductResponse productResponse(@PathVariable("orderId") Integer orderId);

}
