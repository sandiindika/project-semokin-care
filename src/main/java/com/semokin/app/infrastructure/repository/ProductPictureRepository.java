package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPictureRepository extends JpaRepository<ProductPicture, String> {
}