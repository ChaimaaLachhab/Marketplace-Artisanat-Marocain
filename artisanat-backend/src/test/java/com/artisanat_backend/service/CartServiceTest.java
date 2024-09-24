package com.artisanat_backend.service;

import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.repository.CartRepository;
import com.artisanat_backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProductToCart() {
        Long cartId = 1L;
        Long productId = 1L;
        Cart cart = new Cart();
        cart.setProducts(new ArrayList<>());
        Product product = new Product();
        product.setId(productId);
        product.setStock(10);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        cartService.addProductToCart(cartId, productId);

        assertTrue(cart.getProducts().contains(product));
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void addProductToCart_ProductOutOfStock() {
        Long cartId = 1L;
        Long productId = 1L;
        Cart cart = new Cart();
        Product product = new Product();
        product.setStock(0);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> cartService.addProductToCart(cartId, productId));
        verify(cartRepository, never()).save(cart);
    }

    @Test
    void removeProductFromCart() {
        Long cartId = 1L;
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        Cart cart = new Cart();
        cart.setProducts(new ArrayList<>());
        cart.getProducts().add(product);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        cartService.removeProductFromCart(cartId, productId);

        assertFalse(cart.getProducts().contains(product));
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void calculateCartTotal() {
        Long cartId = 1L;
        Cart cart = new Cart();
        Product product1 = new Product();
        product1.setPrice(50.0F);
        Product product2 = new Product();
        product2.setPrice(100.0F);
        cart.setProducts(new ArrayList<>());
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        double total = cartService.calculateCartTotal(cartId);

        assertEquals(150.0, total);
    }

    @Test
    void clearCart() {
        Long cartId = 1L;
        Cart cart = new Cart();
        Product product = new Product();
        cart.setProducts(new ArrayList<>());
        cart.getProducts().add(product);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        cartService.clearCart(cartId);

        assertTrue(cart.getProducts().isEmpty());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void getCart() {
        Long cartId = 1L;
        Cart cart = new Cart();

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        Cart result = cartService.getCart(cartId);

        assertNotNull(result);
        assertEquals(cart, result);
    }
}
