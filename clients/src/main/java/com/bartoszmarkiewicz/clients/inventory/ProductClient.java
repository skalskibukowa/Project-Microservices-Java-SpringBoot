package com.bartoszmarkiewicz.clients.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;

@FeignClient("inventory")
public interface ProductClient {
    @GetMapping("api/v1/inventories/{productId}")
    ResponseEntity<Optional<ProductResponse>> getProductById(@PathVariable("productId") Integer productId);

    @PutMapping("api/v1/inventories/{productId}/updateQuantity")
    void updateProductQuantity(@PathVariable("productId") Integer productId, @RequestBody Map<String, Integer> requestBody);

}
