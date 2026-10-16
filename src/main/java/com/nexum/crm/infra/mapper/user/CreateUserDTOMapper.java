package com.nexum.crm.infra.mapper.user;

import com.nexum.crm.domain.user.User;
import com.nexum.crm.infra.controller.dto.createUser.CreateUserRequest;
import com.nexum.crm.infra.controller.dto.createUser.CreateUserResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateUserDTOMapper {

    public CreateUserResponse to(User user){
        return new CreateUserResponse(user.username(), user.email(), user.role());
    }
    public User to(CreateUserRequest request){
        return new User(null, request.username(), request.password(), request.email(),  request.role(), null, null);
    }
}
