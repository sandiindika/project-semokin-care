package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}