package com.bartoszmarkiewicz.inventory.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fraud")
public class Fraud {


    @Id
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence"
    )
    private Integer id;
    private Integer orderId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
}
