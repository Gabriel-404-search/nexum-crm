package com.nexum.crm.infra.gateways;

import com.nexum.crm.application.gateways.UserGateway;
import com.nexum.crm.domain.user.User;
import com.nexum.crm.infra.config.email.EmailService;
import com.nexum.crm.infra.config.security.SecurityUser;
import com.nexum.crm.infra.config.security.TokenService;
import com.nexum.crm.infra.controller.dto.ForgetPassword;
import com.nexum.crm.infra.controller.dto.login.LoginRequest;
import com.nexum.crm.infra.exceptions.PasswordException;
import com.nexum.crm.infra.mapper.user.UserEntityMapper;
import com.nexum.crm.infra.persistence.user.UserEntity;
import com.nexum.crm.infra.persistence.user.UserRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryGateway implements UserGateway {
    private final UserRepositoryImpl userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;

    public UserRepositoryGateway(UserRepositoryImpl userRepository, UserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder, TokenService tokenService, EmailService emailService) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }
    @Override
    public void createUser(User user) {
        UserEntity userEntity = userEntityMapper.to(user);
        String hashPassword = passwordEncoder.encode(user.password());
             List<String> allUsers = userRepository.findAllPassword();
        boolean passwordAlreadyExists = allUsers.stream()
                .anyMatch(password -> passwordEncoder.matches(user.password(), password));
        //Validando se a senha existe.
        if (passwordAlreadyExists) {
            throw new PasswordException("Senha já cadastrada");
        } else {
            userEntity.setPassword(hashPassword);
            //Criptografia feita
            userRepository.save(userEntity);
            userEntityMapper.to(userEntity);
        }
    }

    @Override
    public ResponseEntity<String> loginUser(LoginRequest request) {
        UserEntity userByEmail = userRepository.findByEmail(request.email());
        if (userByEmail == null) {
            return ResponseEntity.status(401).body("Credenciais invalidas");
        } else if (!passwordEncoder.matches(request.password(), userByEmail.getPassword())) {
            return ResponseEntity.status(401).body("Credenciais invalidas");
        } else if (Boolean.FALSE.equals(userByEmail.getActive())) {
            return ResponseEntity.status(403).body("Conta desativada.");
        } else {
            SecurityUser securityUser = new SecurityUser(userByEmail);
            String token = tokenService.generateToken(securityUser);
            emailService.sendLoginTokenHtml(userByEmail.getEmail(), token, userByEmail.getUsername());
            return ResponseEntity.ok("Login efetuado com sucesso! Verifique o seu email para obter o token de acesso.");
        }
    }

    @Override
    public void forgetPassword(ForgetPassword request) {
        UserEntity user = userRepository.findByEmail(request.email());
        //TODO fazer a logica para atualizar a senha
    }
}