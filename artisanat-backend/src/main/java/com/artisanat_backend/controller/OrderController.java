package com.artisanat_backend.controller;

import com.artisanat_backend.enums.Status;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Order;
import com.artisanat_backend.service.OrderService;
import com.artisanat_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Récupère toutes les commandes.
     * @return Liste des commandes.
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Récupère une commande par son ID.
     * @param id ID de la commande.
     * @return Commande demandée.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Récupère les commandes d'un client authentifié.
     * @param customer L'utilisateur authentifié.
     * @return Liste des commandes du client.
     */
    @GetMapping("/customer")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@AuthenticationPrincipal Customer customer) {
        List<Order> orders = orderService.getOrdersByCustomer(customer);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Récupère les commandes par statut.
     * @param status Statut des commandes.
     * @return Liste des commandes avec le statut spécifié.
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable Status status) {
        try {
            List<Order> orders = orderService.getOrdersByStatus(status);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Crée une commande pour un produit unique pour l'utilisateur authentifié.
     * @param order Commande à créer.
     * @param customer L'utilisateur authentifié.
     * @return Commande créée.
     */
    @PostMapping("/single")
    public ResponseEntity<Order> createOrderForSingleProduct(@RequestBody Order order,
                                                             @AuthenticationPrincipal Customer customer) {
        Order createdOrder = orderService.createOrderForSingleProduct(customer, order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    /**
     * Crée une commande avec plusieurs produits pour l'utilisateur authentifié.
     * @param order Commande à créer.
     * @param customer L'utilisateur authentifié.
     * @return Commande créée.
     */
    @PostMapping("/bulk")
    public ResponseEntity<Order> createOrder(@RequestBody Order order,
                                             @AuthenticationPrincipal Customer customer) {
        Order createdOrder = orderService.createOrder(customer, order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    /**
     * Applique une réduction basée sur les points de fidélité et finalise la commande.
     * @param orderId ID de la commande.
     * @param loyaltyPoints Points de fidélité utilisés.
     * @return Réponse HTTP.
     */
    @PostMapping("/apply-discount/{orderId}")
    public ResponseEntity<Void> applyDiscountAndFinalizeOrder(@PathVariable Long orderId,
                                                              @RequestParam int loyaltyPoints) {
        try {
            orderService.applyDiscountAndFinalizeOrder(orderId, loyaltyPoints);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Supprime une commande par son ID.
     * @param id ID de la commande.
     * @return Réponse HTTP.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
