package com.artisanat_backend.repository;

import com.artisanat_backend.enums.Status;
import com.artisanat_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId (Long customerId);
    List<Order> findByStatus (Status status);
}
