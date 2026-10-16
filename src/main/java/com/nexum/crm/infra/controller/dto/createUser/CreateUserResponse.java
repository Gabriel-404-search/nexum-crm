package com.nexum.crm.infra.controller.dto.createUser;

import com.nexum.crm.domain.user.UserRole;

public record CreateUserResponse (String username, String email, UserRole role) {
}
