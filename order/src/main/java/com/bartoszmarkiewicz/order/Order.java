package com.bartoszmarkiewicz.order;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data // Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {


    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    private Integer orderId;

    @NonNull
    private Integer customerId;

    @NonNull
    private Integer productId;

    @NonNull
    private String productName;

    @NonNull
    private Integer orderAmount;

    @NonNull
    private Double orderValue;

    @NonNull
    private LocalDateTime orderCreatedAt;

    @NonNull
    private String shippingAddress;

    @NonNull
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PROGRESS; // Default Value
}