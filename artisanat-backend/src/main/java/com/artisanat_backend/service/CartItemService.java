package com.artisanat_backend.service;

import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.CartItem;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.repository.CartItemRepository;
import com.artisanat_backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
    }


    @Transactional
    public CartItem addOrUpdateCartItem(Cart cart, Product product, int quantity) {
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    return newItem;
                });

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        return cartItemRepository.save(cartItem);
    }

    public void removeCartItem(Cart cart, Long productId) {
        cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public BigDecimal calculateTotal(Cart cart) {
        return cart.getCartItems().stream()
                .map(item -> BigDecimal.valueOf(item.getProduct().getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

