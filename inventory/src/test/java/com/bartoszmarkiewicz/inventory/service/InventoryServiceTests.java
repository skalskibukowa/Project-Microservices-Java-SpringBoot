package com.bartoszmarkiewicz.inventory.service;

import com.bartoszmarkiewicz.inventory.model.Inventory;
import com.bartoszmarkiewicz.inventory.repository.InventoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTests {
    
    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    // Test method getAllProducts
    @Test
    public void InventoryService_GetAllProducts_shouldReturnAllProducts() {

        // Arrange
        List<Inventory> mockInventories = new ArrayList<>();

        Inventory invetories1 = Inventory.builder()
                .productId(1)
                .productName("Apple")
                .productQuantity(20)
                .productPrice(20.00F)
                .createdAt(LocalDateTime.now())
                .build();

        Inventory invetories2 = Inventory.builder()
                .productId(2)
                .productName("Banana")
                .productQuantity(20)
                .productPrice(20.00F)
                .createdAt(LocalDateTime.now())
                .build();

        mockInventories.add(invetories1);
        mockInventories.add(invetories2);

        // Act
        Mockito.when(inventoryRepository.findAll()).thenReturn(mockInventories);

        List<Inventory> result = inventoryService.getAllProducts();

        // Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).containsExactlyElementsOf(mockInventories);
        Assertions.assertThat(result).asList();

        System.out.println(mockInventories);

    }

    @Test
    public void InventoryService_GetAllProducts_shouldReturnEmptyList() {

        // Arrange
        List<Inventory> mockInventories = new ArrayList<>();

        // Act
        Mockito.when(inventoryRepository.findAll()).thenReturn(mockInventories);

        List<Inventory> result = inventoryService.getAllProducts();

        // Assert
        Assertions.assertThat(result).isEmpty();

    }

    // Test method getProductById

    @Test
    public void InventoryService_GetProductById_ReturnResponseProduct() {
        // Arrange
       int inventoryId = 1;

       Inventory inventory = Inventory.builder()
               .productId(1)
               .productName("Apple")
               .productQuantity(20)
               .productPrice(20.00F)
               .createdAt(LocalDateTime.now())
               .build();

       when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.ofNullable(inventory));

       // Act
       Optional<Inventory> inventoryReturn = inventoryService.getProductById(inventoryId);

       // Assert
       Assertions.assertThat(inventoryReturn).isNotNull();

    }

    @Test
    public void InventoryService_getProductById_WhenExistingProduct_ReturnsOptionalWithInventory() {
        // Arrange
        int productId = 1;
        Inventory mockInventory = Inventory.builder()
                .productId(1)
                .productName("Apple")
                .productQuantity(20)
                .productPrice(20.00F)
                .createdAt(LocalDateTime.now())
                .build();

        Mockito.when(inventoryRepository.findById(anyInt()))
                .thenReturn(Optional.of(mockInventory));

        // Act
        Optional<Inventory> result = inventoryService.getProductById(productId);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(mockInventory);
        assertThat(result.get()).hasFieldOrProperty("productQuantity");
    }

    @Test
    public void InventoryService_getProductById_WhenNonExistingProduct_ReturnsEmptyOptional() {
        // Arrange
        int productId = 2;

        Mockito.when(inventoryRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        // Act
        Optional<Inventory> result = inventoryService.getProductById(productId);

        // Assert
        assertThat(result).isEmpty();
    }


    // Test method updateProduct

    @Test
    public void InventoryService_updateProduct_ReturnProductUpdated() {

        // Arrange
        Integer productId = 1;

        Inventory existingProduct = Inventory.builder()
                .productId(1)
                .productName("Apple")
                .productPrice(20.0F)
                .productQuantity(20)
                .build();

        Inventory updatedProduct = Inventory
                .builder()
                .productId(1)
                .productName("Banana")
                .productPrice(20.0F)
                .productQuantity(20)
                .build();


        Mockito.when(inventoryRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        // Act
        Inventory actualProduct = inventoryService.updateProduct(updatedProduct);

        // Assert
        Assertions.assertThat(actualProduct).isNotNull();
        Assertions.assertThat(actualProduct.getProductId()).isEqualTo(productId);
        Assertions.assertThat(actualProduct.getProductName()).isEqualTo("Banana");

    }

    // Test method updateProductQuantity

    @Test
    public void InventoryService_updateProductQuantity_ReturnNewQuantity() {

        // Arrange
        Integer productId = 1;
        Inventory existingProduct = Inventory.builder()
                .productId(1)
                .productName("Apple")
                .productPrice(20.0F)
                .productQuantity(20)
                .build();

        Mockito.when(inventoryRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        Inventory updatedInventory = existingProduct; // Create a copy
        updatedInventory.setProductQuantity(30); // Set the expected new quantity
        Mockito.when(inventoryService.updateProduct(updatedInventory)).thenReturn(updatedInventory);

        // Act
        Inventory actualProduct = inventoryService.updateProduct(existingProduct);


        // Assert
        Assertions.assertThat(actualProduct).isNotNull();
        Assertions.assertThat(actualProduct.getProductQuantity()).isEqualTo(30);
        Assertions.assertThat(actualProduct.getProductId()).isEqualTo(1);

    }

    // Test method remove product

    @Test
    public void InventoryService_removeProduct_ReturnNull() {

        // Arrange

        Integer productId = 1;

        Inventory existingProduct = Inventory.builder()
                .productId(1)
                .productName("Apple")
                .productPrice(20.0F)
                .productQuantity(20)
                .build();

        Mockito.when(inventoryRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        Mockito.doNothing().when(inventoryRepository).deleteById(productId);

        // Act
        Inventory removedProduct = inventoryService.removeProduct(productId);

        // Assert
        Assertions.assertThat(removedProduct).isNotNull();
        Assertions.assertThat(removedProduct.getProductId()).isEqualTo(1);

        // Test exception handling (optional)
        Mockito.verify(inventoryRepository).findById(productId);
        Mockito.verify(inventoryRepository).deleteById(productId);

    }

}
