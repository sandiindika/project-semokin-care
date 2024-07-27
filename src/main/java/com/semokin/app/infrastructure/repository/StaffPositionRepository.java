package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.StaffPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffPositionRepository extends JpaRepository<StaffPosition, String> {
}