package com.artisanat_backend.dto;

import com.artisanat_backend.model.Customer;

import java.util.List;

/**
 * DTO for {@link com.artisanat_backend.model.Cart}
 */
public class CartDto {
    Long id;
    List<ProductDto> products;
    Customer customer;
}