package com.artisanat_backend.controller;

import com.artisanat_backend.model.Cart;
import com.artisanat_backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    // Ajouter un produit au panier
    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<String> addProductToCart(
            @PathVariable("cartId")  Long cartId,
            @PathVariable("productId")  Long productId) {
        try {
            cartService.addProductToCart(cartId, productId);
            return ResponseEntity.ok("Product added to cart successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Supprimer un produit du panier
    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<String> removeProductFromCart(
            @PathVariable("cartId")  Long cartId,
            @PathVariable("productId")  Long productId) {
        try {
            cartService.removeProductFromCart(cartId, productId);
            return ResponseEntity.ok("Product removed from cart successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Calculer le total du panier
    @GetMapping("/{cartId}/total")
    public ResponseEntity<Double> calculateCartTotal(@PathVariable("cartId")  Long cartId) {
        try {
            double total = cartService.calculateCartTotal(cartId);
            return ResponseEntity.ok(total);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Vider le panier
    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<String> clearCart(@PathVariable("cartId")  Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok("Cart cleared successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Obtenir les détails du panier
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable("cartId")  Long cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
