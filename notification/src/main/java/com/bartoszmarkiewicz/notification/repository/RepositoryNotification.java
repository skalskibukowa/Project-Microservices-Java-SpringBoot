package com.bartoszmarkiewicz.notification.repository;

import com.bartoszmarkiewicz.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryNotification extends JpaRepository<Notification, Integer> {
}
