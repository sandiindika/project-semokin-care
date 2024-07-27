package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {

    Page<Review> findByProductId(String productId, Pageable pageable);
}