package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}