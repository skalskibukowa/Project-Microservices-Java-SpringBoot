package com.bartoszmarkiewicz.inventory;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence"
    )
    private Integer productId;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Column(name = "product_quantity")
    private Float productQuantity;

    @NotNull
    @Column(name = "product_price")
    private Float productPrice;

    @Column(name = "product_createdAt")
    private LocalDateTime createdAt;

    /*
    @PrePersist
    public void prePersist() {
        // This method will be called before the entity is persisted (saved to the database)
        productDateStatus = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        // This method will be called before the entity is updated
        productDateStatus = LocalDateTime.now();
    }

     */
}
