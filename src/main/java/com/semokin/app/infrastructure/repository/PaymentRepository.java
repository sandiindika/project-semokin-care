package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}