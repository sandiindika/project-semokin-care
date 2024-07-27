package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, String> {
}