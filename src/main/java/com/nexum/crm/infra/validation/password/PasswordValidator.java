package com.nexum.crm.infra.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    // Regra: Mínimo 8 caracteres, pelo menos 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).{8,}$";
    private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false; // Ou true se a validação de nulo for tratada por outra anotação como @NotBlank
        }
        return PATTERN.matcher(password).matches();
    }
}