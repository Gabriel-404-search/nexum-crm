package com.nexum.crm.application.usecases;

import com.nexum.crm.application.gateways.UserGateway;
import com.nexum.crm.domain.user.User;
import com.nexum.crm.infra.controller.dto.ForgetPassword;
import com.nexum.crm.infra.controller.dto.login.LoginRequest;
import org.springframework.http.ResponseEntity;

public class UserInteractor {
    private final UserGateway userGateway;

    public UserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void createUser(User user) {
        userGateway.createUser(user);
    }

    public ResponseEntity<String> loginUser(LoginRequest request) {
        return userGateway.loginUser(request);
    }

    public void forgetPassword(ForgetPassword request) {
        forgetPassword(request);
    }

}
