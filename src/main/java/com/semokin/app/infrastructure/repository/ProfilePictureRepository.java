package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, String> {
}