package com.artisanat_backend.repository;

import com.artisanat_backend.model.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyRepository extends JpaRepository<Loyalty, Long> {
    Loyalty findByCustomerId (Long customerId);
}
