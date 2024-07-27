package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, String> {
}