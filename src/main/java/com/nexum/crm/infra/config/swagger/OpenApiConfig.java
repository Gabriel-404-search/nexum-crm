package com.nexum.crm.infra.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    //  indica que o Spring deve gerir este method configuração e injetando as Dependências desse objeto automaticamente.
    @Bean
    public OpenAPI customOpenAPI() {
        //esse openapi é a config do swagger, aqui so estou a colocar algumas informações para aparecer na documentação.
        return new OpenAPI()
                .info(new Info()
                        .title("Nexum-CRM")
                        .version("v1.0")
                        .description("### Time de Desenvolvimento:\n" +
                                "**Gabriel Marques**\n" +
                                "API oficial para o ecossistema Nexum-CRM.")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento Nexum-CRM")
                                .url("https://github.com/Gabriel-404-search/nexum-crm")
                                .email("gabrielbielm9@gmail.com")));
    }
}