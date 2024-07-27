package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}