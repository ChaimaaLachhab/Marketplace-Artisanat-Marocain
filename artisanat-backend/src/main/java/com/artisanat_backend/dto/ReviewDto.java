package com.artisanat_backend.dto;

import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Review;

import java.time.LocalDateTime;

/**
 * DTO for {@link Review}
 */
public class ReviewDto {
    int rating;
    String comment;
    LocalDateTime reviewDate;
    Customer customer;
}