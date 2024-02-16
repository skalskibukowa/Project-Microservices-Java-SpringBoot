package com.bartoszmarkiewicz.inventory.repository;

import com.bartoszmarkiewicz.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("" +
            "SELECT CASE WHEN COUNT(p) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Inventory p " +
            "WHERE p.productName = ?1"
    )
    Boolean selectExistsProduct(String productName);




}
