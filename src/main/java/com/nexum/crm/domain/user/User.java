package com.nexum.crm.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record User(
        UUID id,
        String username,
        String password,
        String email,
        UserRole role,
        LocalDateTime createdAt,
        Boolean active
) {
    public User(UUID id, String username, String password, String email, UserRole role, LocalDateTime createdAt, Boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.active = active;
    }
}