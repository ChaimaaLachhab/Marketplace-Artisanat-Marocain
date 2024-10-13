package com.artisanat_backend.service;

import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Loyalty;
import com.artisanat_backend.model.Order;
import com.artisanat_backend.repository.LoyaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoyaltyService {

    private final LoyaltyRepository loyaltyRepository;

    @Autowired
    public LoyaltyService(LoyaltyRepository loyaltyRepository) {
        this.loyaltyRepository = loyaltyRepository;
    }

    public Optional<Loyalty> getLoyaltyById(Long id) {
        return loyaltyRepository.findById(id);
    }

    public void addPointsToCustomerLoyalty(Customer customer, int points) {
        Loyalty loyalty = customer.getLoyalty();
        if (loyalty == null) {
            loyalty = new Loyalty();
            loyalty.setCustomer(customer);
            customer.setLoyalty(loyalty);
        }
        loyalty.setPoints(loyalty.getPoints() + points);
        loyaltyRepository.save(loyalty);
    }

    public int calculatePoints(double totalAmount) {
        return (int) (totalAmount / 10);
    }

    public double calculateDiscount(int points, double totalAmount) {
        return points * 0.1;
    }

    public void updateLoyaltyPoints(Long customerId, int usedPoints) {
        Loyalty loyalty = loyaltyRepository.findByCustomerId(customerId);
        loyalty.setPoints(loyalty.getPoints() - usedPoints);
        loyaltyRepository.save(loyalty);
    }
}
