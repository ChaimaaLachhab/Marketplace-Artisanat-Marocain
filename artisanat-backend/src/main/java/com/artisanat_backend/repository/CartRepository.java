package com.artisanat_backend.repository;

import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.CartItem;
import com.artisanat_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomerId(Long customerId);
    Cart findByCustomer(Customer customer);
}
