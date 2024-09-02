package com.artisanat_backend.controller;

import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Loyalty;
import com.artisanat_backend.model.Order;
import com.artisanat_backend.service.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loyalty")
public class LoyaltyController {

    @Autowired
    private LoyaltyService loyaltyService;

    // Obtenir les informations de fidélité par ID
    @GetMapping("/{id}")
    public ResponseEntity<Loyalty> getLoyaltyById(@PathVariable("id")  Long id) {
        return loyaltyService.getLoyaltyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Ajouter de nouveaux points de fidélité pour un client
    @PostMapping("/add-points")
    public ResponseEntity<String> addPointsToCustomerLoyalty(
            @RequestParam Long customerId,
            @RequestParam int points) {
        try {
            Customer customer = new Customer();  // You should retrieve the customer from the database
            customer.setId(customerId);  // This should be set based on the actual customer retrieval
            loyaltyService.addPointsToCustomerLoyalty(customer, points);
            return ResponseEntity.ok("Points added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Calculer les points basés sur une commande
    @PostMapping("/calculate-points")
    public ResponseEntity<Integer> calculatePoints(@RequestBody Order order) {
        int points = loyaltyService.calculatePoints(order);
        return ResponseEntity.ok(points);
    }

    // Calculer la réduction basée sur les points
    @GetMapping("/calculate-discount")
    public ResponseEntity<Double> calculateDiscount(
            @RequestParam int points,
            @RequestParam double totalAmount) {
        double discount = loyaltyService.calculateDiscount(points, totalAmount);
        return ResponseEntity.ok(discount);
    }

    // Mettre à jour les points de fidélité d'un client
    @PutMapping("/{customerId}/update-points")
    public ResponseEntity<String> updateLoyaltyPoints(
            @PathVariable("customerId")  Long customerId,
            @RequestParam int usedPoints) {
        try {
            loyaltyService.updateLoyaltyPoints(customerId, usedPoints);
            return ResponseEntity.ok("Loyalty points updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
