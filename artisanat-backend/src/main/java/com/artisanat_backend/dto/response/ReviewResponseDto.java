package com.artisanat_backend.dto.response;

import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private int rating;
    private String comment;
    private LocalDateTime reviewDate;
    private Customer customer;
    private Product product;
}
