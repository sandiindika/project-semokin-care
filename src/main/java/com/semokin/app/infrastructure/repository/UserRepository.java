package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}