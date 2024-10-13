package com.artisanat_backend.service;

import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.CartItem;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.repository.CartRepository;
import com.artisanat_backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
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

    @Mock
    private CartItemService cartItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProductToCart() {
        Long customerId = 1L;
        Long productId = 1L;
        int quantity = 2;

        Customer customer = new Customer();
        customer.setId(customerId);

        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());

        Product product = new Product();
        product.setId(productId);
        product.setStock(10);
        product.setPrice(50.0F); // Assuming price is set

        when(cartRepository.findByCustomerId(customerId)).thenReturn(Optional.of(cart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(cartItemService.addOrUpdateCartItem(cart, product, quantity)).thenReturn(new CartItem()); // Mock CartItem creation

        cartService.addProductToCart(customer, productId, quantity);

        assertFalse(cart.getCartItems().isEmpty()); // Check that cart items are added
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void calculateCartTotal() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());

        Product product1 = new Product();
        product1.setPrice(50.0F);
        CartItem item1 = new CartItem();
        item1.setProduct(product1);
        item1.setQuantity(1);

        Product product2 = new Product();
        product2.setPrice(100.0F);
        CartItem item2 = new CartItem();
        item2.setProduct(product2);
        item2.setQuantity(2);

        cart.getCartItems().add(item1);
        cart.getCartItems().add(item2);

        when(cartRepository.findByCustomerId(customerId)).thenReturn(Optional.of(cart));
        when(cartItemService.calculateTotal(cart)).thenReturn(BigDecimal.valueOf(250.0)); // Mock total calculation

        BigDecimal total = cartService.calculateCartTotal(customerId);

        assertEquals(BigDecimal.valueOf(250.0), total);
    }

    @Test
    void clearCart() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(new Product());
        cart.setCartItems(new ArrayList<>());
        cart.getCartItems().add(cartItem);

        when(cartRepository.findByCustomerId(customerId)).thenReturn(Optional.of(cart));

        cartService.clearCart(customerId);

        assertTrue(cart.getCartItems().isEmpty()); // Ensure the cart is cleared
        verify(cartRepository, times(1)).save(cart); // Ensure save is called
    }

    @Test
    void getCartByCustomer() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        Cart cart = new Cart();

        when(cartRepository.findByCustomerId(customerId)).thenReturn(Optional.of(cart));

        Cart result = cartService.getCartByCustomer(customer);

        assertNotNull(result);
        assertEquals(cart, result);
    }

    @Test
    void getCartItems() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(new Product());
        cart.setCartItems(new ArrayList<>());
        cart.getCartItems().add(cartItem);

        when(cartRepository.findByCustomer(customer)).thenReturn(cart);

        var items = cartService.getCartItems(customer);

        assertEquals(1, items.size());
        assertEquals(cartItem, items.get(0));
    }
}
