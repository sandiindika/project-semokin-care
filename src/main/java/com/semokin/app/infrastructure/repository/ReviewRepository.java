package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {
}