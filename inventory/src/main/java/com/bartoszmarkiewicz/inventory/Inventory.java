package com.bartoszmarkiewicz.inventory;


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
@Table(name = "inventory")
public class Inventory {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence"
    )
    private Long productId;

    private String productName;

    private Long productQuantity;

    private Float productPrice;

    private LocalDateTime productDateStatus;

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

}
