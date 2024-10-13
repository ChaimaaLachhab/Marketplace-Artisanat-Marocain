package com.artisanat_backend.repository;

import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.CartItem;
import com.artisanat_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}