package com.artisanat_backend.service;

import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.CartItem;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.repository.CartRepository;
import com.artisanat_backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productRepository = productRepository;
    }

    @Transactional
    public Cart getCartByCustomer(Customer customer) {
        return cartRepository.findByCustomerId(customer.getId())
                .orElseGet(() -> createNewCart(customer));
    }

    public List<CartItem> getCartItems(Customer customer) {
        Cart cart = cartRepository.findByCustomer(customer);
        return cart != null ? cart.getCartItems() : List.of();
    }

    @Transactional
    public Cart addProductToCart(Customer customer, Long productId, int quantity) {
        Cart cart = cartRepository.findByCustomerId(customer.getId())
                .orElseGet(() -> createNewCart(customer));

        if (cart.getCartItems() == null) {
            cart.setCartItems(new ArrayList<>());
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        CartItem cartItem = cartItemService.addOrUpdateCartItem(cart, product, quantity);

        if (!cart.getCartItems().contains(cartItem)) {
            cart.getCartItems().add(cartItem);
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public void removeProductFromCart(Long customerId, Long productId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        cartItemService.removeCartItem(cart, productId);
    }

    public BigDecimal calculateCartTotal(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        return cartItemService.calculateTotal(cart);
    }

    @Transactional
    public void clearCart(Long customerId) {
        logger.info("Attempting to clear cart for customer ID: {}", customerId);

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> {
                    logger.error("Cart not found for customer ID: {}", customerId);
                    return new EntityNotFoundException("Cart not found");
                });

        logger.info("Cart found for customer ID: {}. Proceeding to clear items.", customerId);

        logger.debug("Clearing items from cart with ID: {}", cart.getId());
        cart.getCartItems().clear();
        logger.debug("Cart items cleared. Current items count: {}", cart.getCartItems().size());        logger.info("Items cleared from cart for customer ID: {}", customerId);

        cartRepository.save(cart);
        logger.info("Cart saved successfully after clearing items for customer ID: {}", customerId);
    }

    private Cart createNewCart(Customer customer) {
        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        return cartRepository.save(newCart);
    }

}
