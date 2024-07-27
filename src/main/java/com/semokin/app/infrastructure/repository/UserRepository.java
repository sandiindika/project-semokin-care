package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findFirstByEmailOrUsername(String username, String email);
}