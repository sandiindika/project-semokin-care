package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(Role.Privilege role);
}