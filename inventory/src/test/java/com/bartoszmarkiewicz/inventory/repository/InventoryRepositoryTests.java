package com.bartoszmarkiewicz.inventory.repository;

import com.bartoszmarkiewicz.inventory.model.Inventory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
public class InventoryRepositoryTests {


    @Autowired
    private InventoryRepository inventoryRepository;


    @Test
    public void Inventory_Save_ReturnSavedInventory() {
        // Arrange

        Inventory inventory = Inventory.builder()
                .productId(1)
                .productName("Apple")
                .productPrice(2.50F)
                .productQuantity(2)
                .createdAt(LocalDateTime.now())
                .build();
        // Act

        Inventory savedInventory = inventoryRepository.save(inventory);

        // Assert
        Assertions.assertThat(savedInventory).isNotNull();
        Assertions.assertThat(savedInventory.getProductId()).isGreaterThan(0);
        Assertions.assertThat(savedInventory.getProductQuantity()).isEqualTo(2);
        Assertions.assertThat(savedInventory.getProductName()).isEqualTo("Apple");

        System.out.println(savedInventory);
    }


}
