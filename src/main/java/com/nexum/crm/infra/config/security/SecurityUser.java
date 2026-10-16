package com.nexum.crm.infra.config.security;

import com.nexum.crm.domain.user.UserRole;
import com.nexum.crm.infra.persistence.user.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final UserEntity userEntity;

    public SecurityUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail(); //Campo de login
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEntity.getActive();
    }
}