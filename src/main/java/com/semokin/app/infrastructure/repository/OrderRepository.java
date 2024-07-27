package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}