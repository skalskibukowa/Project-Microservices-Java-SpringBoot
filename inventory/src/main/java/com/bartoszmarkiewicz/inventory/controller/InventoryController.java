package com.bartoszmarkiewicz.inventory.controller;


import com.bartoszmarkiewicz.inventory.dto.InventoryRecord;
import com.bartoszmarkiewicz.inventory.exceptions.InventoryValidationException;
import com.bartoszmarkiewicz.inventory.model.Inventory;
import com.bartoszmarkiewicz.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/inventories")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody InventoryRecord inventoryProductAddRequest) {

        try {
       /*
            if (inventoryProductAddRequest.getProductQuantity() < 0) {
                throw new ProductValidationException("Product quantity cannot be negative");
            }
        */
            log.info("Adding new product: {}", inventoryProductAddRequest);
            Inventory inventory = inventoryService.addProduct(inventoryProductAddRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(inventory);
        } catch (InventoryValidationException e) {
            return ResponseEntity.badRequest().body(e.getJsonErrorResponse());
        }

    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllProducts() {
        log.info("Retrieving all products");
        List<Inventory> inventories = inventoryService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(inventories);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<Inventory>> getProductById(@PathVariable("productId") Integer productId) {
        log.info("Retrieving product with ID: {}", productId);
        Optional<Inventory> inventory = inventoryService.getProductById(productId);
        return ResponseEntity.status(inventory.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(inventory);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Inventory> updateProduct(@PathVariable("productId") Integer productId,
                                                   @RequestBody Inventory updatedInventory) {
        log.info("Updating product quantity for product with ID: {}", productId);
        Inventory inventory = inventoryService.updateProduct(updatedInventory);
        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Inventory> deleteProductById(@PathVariable("productId") Integer productId) {
        log.info("Deleting product with ID: {}", productId);
        Inventory inventory = inventoryService.removeProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(inventory);
    }

    /*
    @PutMapping(path = "{/productId}")
    public ResponseEntity<?> updateProductQuantity(@PathVariable("productId") Integer productId,
                                                    @RequestBody ProductResponse productResponse) {
            Product inventory = inventoryService.updateProductValue(productResponse);
            return ResponseEntity.status(HttpStatus.OK).body(inventory);

    }
     */

}