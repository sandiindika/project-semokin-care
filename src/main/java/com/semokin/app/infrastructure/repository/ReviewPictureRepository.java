package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.ReviewPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPictureRepository extends JpaRepository<ReviewPicture, String> {

    List<ReviewPicture> findByReviewId(String reviewId);
}