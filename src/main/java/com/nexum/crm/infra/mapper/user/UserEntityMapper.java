package com.nexum.crm.infra.mapper.user;

import com.nexum.crm.domain.user.User;
import com.nexum.crm.infra.persistence.user.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserEntityMapper {

    public UserEntity to(User user){
        UserEntity entity = new UserEntity();
        entity.setId(user.id());
        entity.setUsername(user.username());
        entity.setPassword(user.password());
        entity.setEmail(user.email());
        entity.setRole(user.role());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setActive(true);
        return entity;
    }

    public User to(UserEntity entity){
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getActive()
        );
    }
}
