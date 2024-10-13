package com.artisanat_backend.controller;

import com.artisanat_backend.dto.response.CartResponseDto;
import com.artisanat_backend.mapper.CartMapper;
import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @Autowired
    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PostMapping("/add-product/{productId}")
    public ResponseEntity<CartResponseDto> addProductToCart(
            @AuthenticationPrincipal Customer customer,
            @PathVariable Long productId,
            @RequestParam int quantity) {
        Cart cart = cartService.addProductToCart(customer, productId, quantity);
        CartResponseDto cartDto = cartMapper.toDto(cart);
        return ResponseEntity.ok(cartDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @DeleteMapping("/remove-product/{productId}")
    public ResponseEntity<Void> removeProductFromCart(
            @AuthenticationPrincipal Customer customer,
            @PathVariable Long productId) {
        cartService.removeProductFromCart(customer.getId(), productId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/total")
    public ResponseEntity<BigDecimal> calculateCartTotal(@AuthenticationPrincipal Customer customer) {
        BigDecimal total = cartService.calculateCartTotal(customer.getId());
        return ResponseEntity.ok(total);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal Customer customer) {
        cartService.clearCart(customer.getId());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/get-cart")
    public ResponseEntity<CartResponseDto> getCart(@AuthenticationPrincipal Customer customer) {
        Cart cart = cartService.getCartByCustomer(customer);
        CartResponseDto cartDto = cartMapper.toDto(cart);
        return ResponseEntity.ok(cartDto);
    }
}
