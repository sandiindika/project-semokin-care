package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}