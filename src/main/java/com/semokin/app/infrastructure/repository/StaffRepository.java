package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Customer;
import com.semokin.app.domain.model.Staff;
import com.semokin.app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, String> {
    Optional<Staff> findFirstByUserAndId(User user, String id);
}