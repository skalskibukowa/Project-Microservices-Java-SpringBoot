package com.bartoszmarkiewicz.kafka;

import java.time.LocalDateTime;

public record Message(String message, LocalDateTime created) {
}
