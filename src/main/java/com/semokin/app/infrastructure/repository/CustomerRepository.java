package com.semokin.app.infrastructure.repository;

import com.semokin.app.domain.model.Customer;
import com.semokin.app.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findFirstByUserAndId(User user,String id);
}