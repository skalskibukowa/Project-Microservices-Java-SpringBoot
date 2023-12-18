package com.bartoszmarkiewicz.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("" +
            "SELECT CASE WHEN COUNT(p) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Product p " +
            "WHERE p.productName = ?1"
    )
    Boolean selectExistsProduct(String productName);




}
