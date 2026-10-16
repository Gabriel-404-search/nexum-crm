package com.nexum.crm.application.gateways;

import com.nexum.crm.domain.user.User;
import com.nexum.crm.infra.controller.dto.ForgetPassword;
import com.nexum.crm.infra.controller.dto.login.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface UserGateway {
    void createUser(User user);

    ResponseEntity<String> loginUser(LoginRequest request);

    void forgetPassword(ForgetPassword request);
}
