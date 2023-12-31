package com.bartoszmarkiewicz.notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryNotification extends JpaRepository<Notification, Integer> {
}
