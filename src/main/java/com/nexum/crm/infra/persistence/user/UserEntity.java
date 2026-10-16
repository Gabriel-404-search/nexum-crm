package com.nexum.crm.infra.persistence.user;

import com.nexum.crm.domain.user.UserRole;
import com.nexum.crm.infra.validation.email.ValidEmail;
import com.nexum.crm.infra.validation.password.ValidPassword;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Id do user")
    private UUID id;

    @NotBlank(message = "Nome do usuario incorreto.")
    @Schema(description = "Nome de usuario")
    private String username;

    @ValidPassword
    @Schema(description = "Senha criptografada")
    private String password;

    @ValidEmail
    @Schema(description = "Email que deve unico")
    private String email;

    @Schema(description = "Criado no momento em que é salvo no DB")
    private LocalDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Nivel de acesso por USER ou ADMIN")
    private UserRole  role;

    @Schema(description = "Informa se o usuario esta ativo ou inativo")
    private Boolean active;
}
