package com.nexum.crm.infra.controller.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String email,@NotBlank String password) {
}
