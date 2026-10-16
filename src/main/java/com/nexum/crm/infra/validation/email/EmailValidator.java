package com.nexum.crm.infra.validation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    // Regex atualizado, mais flexível e seguro para qualquer extensão válida (ex: .com, .com.br, .tech)
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return true; // Deixamos o @NotBlank lidar com nulos/vazios
        }

        // LOG DE DEBUG: Olhe para o seu console do Spring Boot quando enviar a requisição no Postman!
        System.out.println(">>> Validando email recebido: [" + email + "]");

        boolean isValid = email.matches(EMAIL_REGEX);
        System.out.println(">>> O email passou no regex? " + isValid);

        return isValid;
    }
}
