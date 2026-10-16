package com.nexum.crm.infra.validation.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class) // Aponta para a classe que vai validar
@Target({ElementType.FIELD, ElementType.PARAMETER}) // Onde a anotação pode ser usada
@Retention(RetentionPolicy.RUNTIME) // Disponível em tempo de execução
public @interface ValidEmail {

    // Mensagem padrão caso a validação falhe
    String message() default "Por favor, digite um email válido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}