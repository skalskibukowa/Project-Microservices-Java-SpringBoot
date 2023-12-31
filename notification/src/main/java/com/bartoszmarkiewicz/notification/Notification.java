package com.bartoszmarkiewicz.notification;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {


    @Id
    @SequenceGenerator(
            name ="notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )

    private Integer notificationId;
    private Integer toOrderId;
    private Integer toCustomerId;
    private Integer toProductId;
    private String sender;
    private String message;
    private LocalDateTime sentAt;
}
