package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findByIsDeletedFalse(Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByCategoryId(String categoryId, Pageable pageable);
    Page<Product> findByBrandId(String brandId, Pageable pageable);
    Page<Product> findByPriceBetween(Long minPrice, Long maxPrice, Pageable pageable);

}