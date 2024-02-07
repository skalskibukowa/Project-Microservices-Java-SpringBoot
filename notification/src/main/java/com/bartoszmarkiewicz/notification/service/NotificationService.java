package com.bartoszmarkiewicz.notification.service;

import com.bartoszmarkiewicz.clients.notification.NotificationRequest;
import com.bartoszmarkiewicz.notification.model.Notification;
import com.bartoszmarkiewicz.notification.repository.RepositoryNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private RepositoryNotification repositoryNotification;

    public void send(NotificationRequest notificationRequest) {
        repositoryNotification.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toOrderId(notificationRequest.toOrderId())
                        .toProductId(notificationRequest.toProductId())
                        .sender("Bartosz Markiewicz")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}
