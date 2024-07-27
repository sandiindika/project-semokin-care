package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {
}