package com.bartoszmarkiewicz.inventory.service;


import com.bartoszmarkiewicz.clients.order.OrderClient;
import com.bartoszmarkiewicz.inventory.dto.InventoryRecord;
import com.bartoszmarkiewicz.inventory.exceptions.InventoryNotFoundException;
import com.bartoszmarkiewicz.inventory.exceptions.InventoryValidationException;
import com.bartoszmarkiewicz.inventory.model.Inventory;
import com.bartoszmarkiewicz.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class InventoryService {

    private static final int MIN_PRODUCT_QUANTITY = 0;
    private static final int MIN_PRODUCT_PRICE = 0;

    private final InventoryRepository inventoryRepository;

    private final OrderClient orderClient;

    // Add products

    public Inventory addProduct(InventoryRecord inventoryProductAddRequest) {

        boolean productExists = inventoryRepository.selectExistsProduct(inventoryProductAddRequest.productName());


        if (productExists) {
            throw new InventoryValidationException("Product " + inventoryProductAddRequest.getProductName() + " taken");
        }


        if (inventoryProductAddRequest.getProductQuantity() < MIN_PRODUCT_QUANTITY) {
            throw new InventoryValidationException("Product quantity cannot be negative");
        }

        if (inventoryProductAddRequest.getProductPrice() < MIN_PRODUCT_PRICE) {
            throw new InventoryValidationException("Product price cannot be negative");
        }

        Inventory inventory = Inventory.builder()
                .productId(inventoryProductAddRequest.productId())
                .productName(inventoryProductAddRequest.productName())
                .productPrice(inventoryProductAddRequest.productPrice())
                .productQuantity(inventoryProductAddRequest.productQuantity())
                .createdAt(inventoryProductAddRequest.createdAt(LocalDateTime.now()))
                .build();

        inventoryRepository.saveAndFlush(inventory);
        log.info("Product with ID {} added successfully", inventory.getProductId());

        return inventory;
    }

    // Get all products
    public List<Inventory> getAllProducts() {
        return inventoryRepository.findAll();
    }

    // Get ProductId
    public Optional<Inventory> getProductById(Integer productId) {
        return inventoryRepository.findById(productId);
    }

    // Update Product
    @Transactional
    public Inventory updateProduct(Inventory updatedInventory) {

        Inventory existingInventory = inventoryRepository.findById(updatedInventory.getProductId())
                .orElseThrow(() -> new InventoryValidationException("Product not found"));

        existingInventory.setProductName(updatedInventory.getProductName());
        existingInventory.setProductPrice(updatedInventory.getProductPrice());
        existingInventory.setProductQuantity(updatedInventory.getProductQuantity());
        existingInventory.setUpdatedAt(LocalDateTime.now());

        inventoryRepository.saveAndFlush(updatedInventory);

        return updatedInventory;
    }

    // Remove Product
    public Inventory removeProduct(Integer productId) {

        return inventoryRepository.findById(productId)
                .map(inventory -> {
                    inventoryRepository.deleteById(productId);
                    log.info("Product with ID {} removed successfully", productId);
                    return inventory;
                }).orElseThrow(() -> new InventoryNotFoundException(productId));
    }
}