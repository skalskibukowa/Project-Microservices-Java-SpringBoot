package com.bartoszmarkiewicz.inventory;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    // AddProduct method to inventory
    public void addProduct(InventoryProductAddRequest inventoryProductAddRequest) {
        Inventory inventory = Inventory.builder()
                .productId(inventoryProductAddRequest.productId())
                .productName(inventoryProductAddRequest.productName())
                .productPrice(inventoryProductAddRequest.productPrice())
                .productQuantity(inventoryProductAddRequest.productQuantity())
                .productDateStatus(inventoryProductAddRequest.productDateStatus())
                .build();

        inventoryRepository.saveAndFlush(inventory);
    }


}


