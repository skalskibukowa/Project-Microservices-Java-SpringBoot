package com.bartoszmarkiewicz.inventory;


import com.bartoszmarkiewicz.inventory.exceptions.InventoryNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Slf4j
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

    // GetProducts
    public List<Inventory> getAllProducts() {
        try {
            return inventoryRepository.findAll();
        } catch (Exception e) {
            log.error("The list of products don't exist");
            return null;
        }
    }


    // Retrieve specific product by productId
    public Optional<Inventory> getProductById(Integer productId) {
        return Optional.ofNullable(inventoryRepository.findById(productId).orElseThrow(() -> new InventoryNotFoundException(productId)));
    }

    // update product quantity


    public void updateProductQuantity(Integer productId, Long newQuantity) {

        try {
            Inventory inventory = inventoryRepository.findById(productId)
                    .orElseThrow(() -> new InventoryNotFoundException(productId));

            // Condition to not allow quantity update by negative value
            if (newQuantity < 0) {
                throw new IllegalArgumentException("Product quantity cannot be updated with a negative value: " + newQuantity);

                // log.error("Product quantity cannot be updated with negative value: {}", newQuantity);
                // return;
            }

            inventory.setProductQuantity(newQuantity);
            inventoryRepository.saveAndFlush(inventory);
        } catch (InventoryNotFoundException e) {
            // Exception
            log.error("Failed to update product quantity: Product not found", e);
        }

    }

    // remove Product by ProductId
    public void removeProduct(Integer productId) {
        try {
            inventoryRepository.deleteById(productId);
        } catch (InventoryNotFoundException e) {
            //Exception
            log.error("Failed to remove product: Product not found", e);
        }
    }


}


