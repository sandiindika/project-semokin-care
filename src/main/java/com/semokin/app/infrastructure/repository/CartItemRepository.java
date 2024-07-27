package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
}