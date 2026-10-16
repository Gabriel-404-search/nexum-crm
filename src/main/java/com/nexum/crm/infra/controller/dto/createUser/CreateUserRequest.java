package com.nexum.crm.infra.controller.dto.createUser;

import com.nexum.crm.domain.user.UserRole;

public record CreateUserRequest(String username, String password, String email, UserRole role) {
}
