package com.artisanat_backend.service;

import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Loyalty;
import com.artisanat_backend.model.Order;
import com.artisanat_backend.repository.LoyaltyRepository;
import com.artisanat_backend.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoyaltyServiceTest {

    @InjectMocks
    private LoyaltyService loyaltyService;

    @Mock
    private LoyaltyRepository loyaltyRepository;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLoyaltyById() {
        Long loyaltyId = 1L;
        Loyalty loyalty = new Loyalty();
        loyalty.setId(loyaltyId);

        when(loyaltyRepository.findById(loyaltyId)).thenReturn(Optional.of(loyalty));

        Optional<Loyalty> result = loyaltyService.getLoyaltyById(loyaltyId);

        assertTrue(result.isPresent());
        assertEquals(loyaltyId, result.get().getId());
    }

    @Test
    void addPointsToCustomerLoyalty() {
        Customer customer = new Customer();
        Loyalty loyalty = new Loyalty();
        loyalty.setPoints(50);
        customer.setLoyalty(loyalty);
        int additionalPoints = 20;

        when(loyaltyRepository.save(loyalty)).thenReturn(loyalty);

        loyaltyService.addPointsToCustomerLoyalty(customer, additionalPoints);

        assertEquals(70, loyalty.getPoints());
        verify(loyaltyRepository, times(1)).save(loyalty);
    }

    @Test
    void addPointsToCustomerLoyalty_CreatesNewLoyalty() {
        Customer customer = new Customer();
        customer.setLoyalty(null);
        int additionalPoints = 20;

        loyaltyService.addPointsToCustomerLoyalty(customer, additionalPoints);

        assertNotNull(customer.getLoyalty());
        assertEquals(additionalPoints, customer.getLoyalty().getPoints());
        verify(loyaltyRepository, times(1)).save(customer.getLoyalty());
    }


    @Test
    void calculateDiscount() {
        int points = 30;
        double totalAmount = 100.0;

        double discount = loyaltyService.calculateDiscount(points, totalAmount);

        assertEquals(3.0, discount);
    }

    @Test
    void updateLoyaltyPoints() {
        Long customerId = 1L;
        int usedPoints = 10;
        Loyalty loyalty = new Loyalty();
        loyalty.setPoints(50);

        when(loyaltyRepository.findByCustomerId(customerId)).thenReturn(loyalty);

        loyaltyService.updateLoyaltyPoints(customerId, usedPoints);

        assertEquals(40, loyalty.getPoints());
        verify(loyaltyRepository, times(1)).save(loyalty);
    }
}
