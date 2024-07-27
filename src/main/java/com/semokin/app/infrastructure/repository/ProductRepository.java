package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Category;
import com.semokin.app.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByCategoryId(String categoryId, Pageable pageable);
    Page<Product> findByBrandId(String brandId, Pageable pageable);
    Page<Product> findByPriceBetween(Long minPrice, Long maxPrice, Pageable pageable);

}