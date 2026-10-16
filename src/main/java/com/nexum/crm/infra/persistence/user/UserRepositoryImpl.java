package com.nexum.crm.infra.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepositoryImpl extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
    @Query("SELECT u.password FROM UserEntity u")
    List<String> findAllPassword();
}
