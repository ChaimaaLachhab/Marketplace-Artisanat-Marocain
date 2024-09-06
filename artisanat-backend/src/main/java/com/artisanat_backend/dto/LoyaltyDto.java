package com.artisanat_backend.dto;

import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Loyalty;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Loyalty}
 */
public class LoyaltyDto {
    int points;
    Customer customer;
}