package com.nexum.crm.infra.controller;

import com.nexum.crm.application.usecases.UserInteractor;
import com.nexum.crm.domain.user.User;
import com.nexum.crm.infra.controller.dto.ForgetPassword;
import com.nexum.crm.infra.controller.dto.createUser.CreateUserRequest;
import com.nexum.crm.infra.controller.dto.createUser.CreateUserResponse;
import com.nexum.crm.infra.controller.dto.login.LoginRequest;
import com.nexum.crm.infra.mapper.user.CreateUserDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/nexum-api")
@Tag(name = "Users", description = "Criação e login de usuarios no sistema")
public class UserController {
    private final UserInteractor userInteractor;
    private final CreateUserDTOMapper mapper;

    public UserController(UserInteractor userInteractor, CreateUserDTOMapper mapper) {
        this.userInteractor = userInteractor;
        this.mapper = mapper;
    }


    @PostMapping("/users")
    @Operation(summary = "Recebe os dados e cria um novo usuario no sistema.")
    @ApiResponse(responseCode = "203", description = "Usuario criado com sucesso")
    @ApiResponse(responseCode = "403", description = "Dados invalidos")
    @Schema(implementation = CreateUserRequest.class)
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request, UriComponentsBuilder uriBuilder) {
        User user = mapper.to(request);
        userInteractor.createUser(user);
        URI uri = uriBuilder.path("/nexum-api/users/{email}").buildAndExpand(user.email()).toUri();
        return ResponseEntity.created(uri).body(mapper.to(user));
    }

    @GetMapping("/auth")
    public ResponseEntity<String> authenticateUser(@RequestBody @Valid LoginRequest request){
        ResponseEntity<String> message = userInteractor.loginUser(request);
        return message;
    }
    @PutMapping("/forget-password")
    public ResponseEntity<Void> forgetPassword(@RequestBody ForgetPassword request){
        userInteractor.forgetPassword(request);
        return ResponseEntity.ok().build();
    }
}
