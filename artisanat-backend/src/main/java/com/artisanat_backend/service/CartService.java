package com.artisanat_backend.service;

import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.repository.CartRepository;
import com.artisanat_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() > 0) {
            cart.getProducts().add(product);
            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Product is out of stock");
        }
    }

    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getProducts().removeIf(product -> product.getId().equals(productId));
        cartRepository.save(cart);
    }

    public double calculateCartTotal(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getProducts().stream().mapToDouble(Product::getPrice).sum();
    }

    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getProducts().clear();
        cartRepository.save(cart);
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }
}
