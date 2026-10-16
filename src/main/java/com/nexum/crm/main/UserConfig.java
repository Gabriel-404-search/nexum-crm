package com.nexum.crm.main;

import com.nexum.crm.application.gateways.UserGateway;
import com.nexum.crm.application.usecases.UserInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserInteractor createUserInteractor(UserGateway userGateway) {
        // O Spring acha o userGateway (que está lá na infra) e injeta aqui
        return new UserInteractor(userGateway);
    }

}