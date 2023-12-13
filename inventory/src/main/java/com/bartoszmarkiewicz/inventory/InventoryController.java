package com.bartoszmarkiewicz.inventory;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/inventories")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public void addProducts(@RequestBody InventoryProductAddRequest inventoryProductAddRequest) {
        log.info("new product has been added {}", inventoryProductAddRequest);
        inventoryService.addProduct(inventoryProductAddRequest);
    }

}