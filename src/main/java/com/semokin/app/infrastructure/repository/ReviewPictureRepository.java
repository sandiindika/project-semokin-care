package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.ReviewPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewPictureRepository extends JpaRepository<ReviewPicture, String> {
}