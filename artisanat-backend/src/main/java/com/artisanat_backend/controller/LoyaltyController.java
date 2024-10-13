package com.artisanat_backend.controller;

import com.artisanat_backend.dto.response.LoyaltyResponseDto;
import com.artisanat_backend.mapper.LoyaltyMapper;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Loyalty;
import com.artisanat_backend.model.Order;
import com.artisanat_backend.service.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loyalty")
public class LoyaltyController {

    private final LoyaltyService loyaltyService;
    private final LoyaltyMapper loyaltyMapper;

    public LoyaltyController(LoyaltyService loyaltyService, LoyaltyMapper loyaltyMapper) {
        this.loyaltyService = loyaltyService;
        this.loyaltyMapper = loyaltyMapper;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<LoyaltyResponseDto> getLoyaltyById(@PathVariable("id") Long id) {
        return loyaltyService.getLoyaltyById(id)
                .map(loyalty -> ResponseEntity.ok(loyaltyMapper.toDto(loyalty))) // Map to DTO
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PostMapping("/add-points")
    public ResponseEntity<String> addPointsToCustomerLoyalty(
            @RequestParam Long customerId,
            @RequestParam int points) {
        try {
            Customer customer = new Customer();
            customer.setId(customerId);
            loyaltyService.addPointsToCustomerLoyalty(customer, points);
            return ResponseEntity.ok("Points added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/calculate-discount")
    public ResponseEntity<Double> calculateDiscount(
            @RequestParam int points,
            @RequestParam double totalAmount) {
        double discount = loyaltyService.calculateDiscount(points, totalAmount);
        return ResponseEntity.ok(discount);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PutMapping("/{customerId}/update-points")
    public ResponseEntity<String> updateLoyaltyPoints(
            @PathVariable("customerId") Long customerId,
            @RequestParam int usedPoints) {
        try {
            loyaltyService.updateLoyaltyPoints(customerId, usedPoints);
            return ResponseEntity.ok("Loyalty points updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
