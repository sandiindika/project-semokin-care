package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}