package com.bartoszmarkiewicz.clients.notification;

import java.time.LocalDateTime;

public record NotificationRequest(
        Integer toCustomerId,
        Integer toOrderId,
        Integer toProductId,
        String message
) {
}
