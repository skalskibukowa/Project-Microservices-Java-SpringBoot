package com.bartoszmarkiewicz.inventory;


import com.bartoszmarkiewicz.inventory.exceptions.ProductValidationException;
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
public class ProductController {

    private final ProductService inventoryService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRecord inventoryProductAddRequest) {

        try {
       /*
            if (inventoryProductAddRequest.getProductQuantity() < 0) {
                throw new ProductValidationException("Product quantity cannot be negative");
            }
        */
            log.info("Adding new product: {}", inventoryProductAddRequest);
            Product inventory = inventoryService.addProduct(inventoryProductAddRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(inventory);
        } catch (ProductValidationException e) {
            return ResponseEntity.badRequest().body(e.getJsonErrorResponse());
        }

    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("Retrieving all products");
        List<Product> inventories = inventoryService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(inventories);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("productId") Integer productId) {
        log.info("Retrieving product with ID: {}", productId);
        Optional<Product> inventory = inventoryService.getProductById(productId);
        return ResponseEntity.status(inventory.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(inventory);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProductQuantity(@PathVariable("productId") Integer productId,
                                                         @RequestBody Product updatedInventory) {
        log.info("Updating product quantity for product with ID: {}", productId);
        Product inventory = inventoryService.updateProduct(updatedInventory);
        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("productId") Integer productId) {
        log.info("Deleting product with ID: {}", productId);
        Product inventory = inventoryService.removeProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(inventory);
    }


}