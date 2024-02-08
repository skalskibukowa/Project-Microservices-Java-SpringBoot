package com.bartoszmarkiewicz.inventory.service;


import com.bartoszmarkiewicz.clients.order.OrderClient;
import com.bartoszmarkiewicz.inventory.dto.ProductRecord;
import com.bartoszmarkiewicz.inventory.exceptions.ProductNotFoundException;
import com.bartoszmarkiewicz.inventory.exceptions.ProductValidationException;
import com.bartoszmarkiewicz.inventory.model.Product;
import com.bartoszmarkiewicz.inventory.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private static final int MIN_PRODUCT_QUANTITY = 0;
    private static final int MIN_PRODUCT_PRICE = 0;

    private final ProductRepository inventoryRepository;

    private final OrderClient orderClient;

    // Add products

    public Product addProduct(ProductRecord inventoryProductAddRequest) {

        boolean productExists = inventoryRepository.selectExistsProduct(inventoryProductAddRequest.productName());


        if (productExists) {
            throw new ProductValidationException("Product " + inventoryProductAddRequest.getProductName() + " taken");
        }


        if (inventoryProductAddRequest.getProductQuantity() < MIN_PRODUCT_QUANTITY) {
            throw new ProductValidationException("Product quantity cannot be negative");
        }

        if (inventoryProductAddRequest.getProductPrice() < MIN_PRODUCT_PRICE) {
            throw new ProductValidationException("Product price cannot be negative");
        }

        Product inventory = Product.builder()
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
    public List<Product> getAllProducts() {
        return inventoryRepository.findAll();
    }

    // Get ProductId
    public Optional<Product> getProductById(Integer productId) {
        return inventoryRepository.findById(productId);
    }

    // Update Product
    public Product updateProduct(Product updatedInventory) {
        return inventoryRepository.saveAndFlush(updatedInventory);
    }

    // Update Qty Product
    public Product updateProductQuantity(Integer productId, Product product) {

        Product existingProduct = getProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Integer newQuantity = product.getProductQuantity();

        existingProduct.setProductQuantity(newQuantity);

        return updateProduct(existingProduct);
    }

    // Remove Product
    public Product removeProduct(Integer productId) {

        return inventoryRepository.findById(productId)
                .map(inventory -> {
                    inventoryRepository.deleteById(productId);
                    log.info("Product with ID {} removed successfully", productId);
                    return inventory;
                }).orElseThrow(() -> new ProductNotFoundException(productId));
    }
}